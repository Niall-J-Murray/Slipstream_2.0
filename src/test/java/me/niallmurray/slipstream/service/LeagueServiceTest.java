package me.niallmurray.slipstream.service;

import me.niallmurray.slipstream.domain.Driver;
import me.niallmurray.slipstream.domain.League;
import me.niallmurray.slipstream.domain.Team;
import me.niallmurray.slipstream.domain.User;
import me.niallmurray.slipstream.repositories.LeagueRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {LeagueService.class})
@ExtendWith(SpringExtension.class)
class LeagueServiceTest {
  @MockBean
  private DriverService driverService;
  @MockBean
  private LeagueRepository leagueRepository;
  @Autowired
  private LeagueService leagueService;

  private static User createNewUser(Team team) {
    User user2 = new User();
    user2.setAuthorities(new HashSet<>());
    user2.setEmail("jane.doe@example.org");
    user2.setLastLogout("Last Logout");
    user2.setPassword("password");
    user2.setTeam(team);
    user2.setUserId(1L);
    user2.setUsername("janedoe");
    return user2;
  }

  private static User createNewUser() {
    return createNewUser(new Team());
  }

  private static League createNewLeague(ArrayList<Team> teams) {
    League league3 = new League();
    league3.setActiveTimestamp("Active Timestamp");
    league3.setCreationTimestamp("Creation Timestamp");
    league3.setIsActive(true);
    league3.setLeagueId(1L);
    league3.setLeagueName("League Name");
    league3.setTeams(teams);
    return league3;
  }

  private static League createNewLeague() {
    return createNewLeague(new ArrayList<>());
  }

  private static Team createNewTeam(League league2, User user) {
    Team team = new Team();
    team.setDrivers(new ArrayList<>());
    team.setFirstPickNumber(10);
    team.setLeague(league2);
    team.setRanking(1);
    team.setSecondPickNumber(10);
    team.setStartingPoints(10.0d);
    team.setTeamId(1L);
    team.setTeamName("Team Name");
    team.setTeamPoints(10.0d);
    team.setUser(user);
    return team;
  }

  private static Driver createNewDriver() {
    Driver driver = new Driver();
    driver.setCarNumber(10);
    driver.setConstructor("Constructor");
    driver.setDateOfBirth("2020-03-01");
    driver.setDriverId(1L);
    driver.setFirstName("Jane");
    driver.setNationality("Nationality");
    driver.setPoints(10.0d);
    driver.setShortName("Short Name");
    driver.setStanding(1);
    driver.setSurname("Doe");
    driver.setTeams(new ArrayList<>());
    driver.setWikiPage("Wiki Page");
    return driver;
  }

  @Test
  void testCreateLeague() {
    League league = createNewLeague();
    when(leagueRepository.save(Mockito.any())).thenReturn(league);
    when(leagueRepository.findAll()).thenReturn(new ArrayList<>());
    assertSame(league, leagueService.createLeague());
    verify(leagueRepository).save(Mockito.any());
    verify(leagueRepository).findAll();
  }

  @Test
  void testFindNewestLeague() {
    League league = createNewLeague();
    when(leagueRepository.save(Mockito.any())).thenReturn(league);
    when(leagueRepository.findAll()).thenReturn(new ArrayList<>());
    assertSame(league, leagueService.findNewestLeague());
    verify(leagueRepository).save(Mockito.any());
    verify(leagueRepository, atLeast(1)).findAll();
  }

  @Test
  void testFindNewestLeague2() {
    League league = createNewLeague();

    League league2 = createNewLeague();

    ArrayList<League> leagueList = new ArrayList<>();
    leagueList.add(league2);
    when(leagueRepository.save(Mockito.any())).thenReturn(league);
    when(leagueRepository.findAll()).thenReturn(leagueList);
    assertSame(league2, leagueService.findNewestLeague());
    verify(leagueRepository).findAll();
  }

  @Test
  void testGetCurrentPickNumber() {
    when(driverService.getUndraftedDrivers(Mockito.any())).thenReturn(new ArrayList<>());

    League league = createNewLeague();
    assertEquals(21, leagueService.getCurrentPickNumber(league));
    verify(driverService).getUndraftedDrivers(Mockito.any());
  }

  @Test
  void testActivateLeague() {
    League league = createNewLeague();
    leagueService.activateLeague(league);
    assertTrue(league.getIsActive());
  }

  @Test
  void testActivateLeague2() {
    League league = mock(League.class);
    doNothing().when(league).setActiveTimestamp(Mockito.any());
    doNothing().when(league).setCreationTimestamp(Mockito.any());
    doNothing().when(league).setIsActive(Mockito.<Boolean>any());
    doNothing().when(league).setLeagueId(Mockito.<Long>any());
    doNothing().when(league).setLeagueName(Mockito.any());
    doNothing().when(league).setTeams(Mockito.any());
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    league.setTeams(new ArrayList<>());
    leagueService.activateLeague(league);
    verify(league, atLeast(1)).setActiveTimestamp(Mockito.any());
    verify(league).setCreationTimestamp(Mockito.any());
    verify(league, atLeast(1)).setIsActive(Mockito.<Boolean>any());
    verify(league).setLeagueId(Mockito.<Long>any());
    verify(league).setLeagueName(Mockito.any());
    verify(league).setTeams(Mockito.any());
  }

  @Test
  void testFindAll() {
    ArrayList<League> leagueList = new ArrayList<>();
    when(leagueRepository.findAll()).thenReturn(leagueList);
    List<League> actualFindAllResult = leagueService.findAll();
    assertSame(leagueList, actualFindAllResult);
    assertTrue(actualFindAllResult.isEmpty());
    verify(leagueRepository).findAll();
  }

  @Test
  void testSave() {
    League league = new League();
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    ArrayList<Team> teams = new ArrayList<>();
    league.setTeams(teams);
    when(leagueRepository.save(Mockito.any())).thenReturn(league);

    League league2 = new League();
    league2.setActiveTimestamp("Active Timestamp");
    league2.setCreationTimestamp("Creation Timestamp");
    league2.setIsActive(true);
    league2.setLeagueId(1L);
    league2.setLeagueName("League Name");
    league2.setTeams(new ArrayList<>());
    leagueService.save(league2);
    verify(leagueRepository).save(Mockito.any());
    assertEquals("Active Timestamp", league2.getActiveTimestamp());
    assertEquals(teams, league2.getTeams());
    assertEquals("League Name", league2.getLeagueName());
    assertEquals(1L, league2.getLeagueId().longValue());
    assertTrue(league2.getIsActive());
    assertEquals("Creation Timestamp", league2.getCreationTimestamp());
    assertTrue(leagueService.findAll().isEmpty());
  }
}

