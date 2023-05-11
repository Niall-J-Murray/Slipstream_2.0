package me.niallmurray.slipstream.service;

import me.niallmurray.slipstream.domain.Driver;
import me.niallmurray.slipstream.domain.League;
import me.niallmurray.slipstream.repositories.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class LeagueService {
  @Autowired
  private LeagueRepository leagueRepository;
  @Autowired
  private DriverService driverService;

  public League createLeague() {
    League league = new League();
    league.setLeagueName("League # " + (leagueRepository.findAll().size() + 1));
    league.setTeams(new ArrayList<>());
    league.setIsActive(false);
    league.setCreationTimestamp(
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yy HH:mm")));
    return leagueRepository.save(league);
  }

  public League findNewestLeague() {
    List<League> allLeagues = leagueRepository.findAll();
    if (allLeagues.size() < 1) {
      return createLeague();
    }
    // If leagues exist, get the latest created (last in list)
    return allLeagues.get(allLeagues.size() - 1);
  }

  public void adminRemoveTeamFromLeague(Long leagueId, Long teamId) {
    League league = findById(leagueId);
    league.getTeams().removeIf(team -> Objects.equals(team.getTeamId(), teamId));
  }

  public int getCurrentPickNumber(League league) {
    List<Driver> undraftedDrivers = driverService.getUndraftedDrivers(league);
    if (undraftedDrivers.size() == 0) {
      activateLeague(league);
    }
    return 21 - undraftedDrivers.size();
  }

  public void activateLeague(League league) {
    league.setIsActive(true);
    league.setActiveTimestamp(
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yy HH:mm")));
  }

  public List<League> findAll() {
    return leagueRepository.findAll();
  }

  public League findById(Long leagueId) {
    return leagueRepository.findById(leagueId).orElse(null);
  }

  public void save(League league) {
    leagueRepository.save(league);
  }
}
