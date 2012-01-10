CREATE DATABASE bdb_post_2010;

use bdb_post_2010;

--
-- Table structure for table `Master`
--
DROP TABLE IF EXISTS `Master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Master` (
  `lahmanID` int(9) NOT NULL auto_increment,
  `playerID` varchar(10) NOT NULL default '',
  `managerID` varchar(10) NOT NULL default '',
  `hofID` varchar(10) NOT NULL default '',
  `birthYear` int(4) default NULL,
  `birthMonth` int(2) default NULL,
  `birthDay` int(2) default NULL,
  `birthCountry` varchar(50) default NULL,
  `birthState` char(2) default NULL,
  `birthCity` varchar(50) default NULL,
  `deathYear` int(4) default NULL,
  `deathMonth` int(2) default NULL,
  `deathDay` int(2) default NULL,
  `deathCountry` varchar(50) default NULL,
  `deathState` char(2) default NULL,
  `deathCity` varchar(50) default NULL,
  `nameFirst` varchar(50) default NULL,
  `nameLast` varchar(50) NOT NULL default '',
  `nameNote` varchar(255) default NULL,
  `nameGiven` varchar(255) default NULL,
  `nameNick` varchar(255) default NULL,
  `weight` int(3) default NULL,
  `height` double(4,1) default NULL,
  `bats` enum('L','R','B') default NULL,
  `throws` enum('L','R','B') default NULL,
  `debut` date default NULL,
  `finalGame` date default NULL,
  `college` varchar(50) default NULL,
  `lahman40ID` varchar(9) default NULL,
  `lahman45ID` varchar(9) default NULL,
  `retroID` varchar(9) default NULL,
  `holtzID` varchar(9) default NULL,
  `bbrefID` varchar(9) default NULL,
  PRIMARY KEY  (`lahmanID`),
  KEY `playerID` (`playerID`),
  KEY `managerID` (`managerID`),
  KEY `hofID` (`hofID`),
  KEY `lahman40ID` (`lahman40ID`),
  KEY `lahman45ID` (`lahman45ID`),
  KEY `bbrefID` (`bbrefID`),
  KEY `bbrefID_2` (`bbrefID`),
  KEY `retroID` (`retroID`,`bbrefID`),
  KEY `holtzID` (`holtzID`),
  KEY `bbrefID_3` (`bbrefID`)
) ENGINE=MyISAM AUTO_INCREMENT=18968 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `Schools`
--

DROP TABLE IF EXISTS `Schools`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Schools` (
  `schoolID` varchar(15) NOT NULL default '',
  `schoolName` varchar(255) NOT NULL default '',
  `schoolCity` varchar(55) default NULL,
  `schoolState` varchar(55) default NULL,
  `schoolNick` varchar(55) default NULL,
  PRIMARY KEY  (`schoolID`),
  KEY `schoolID` (`schoolID`,`schoolName`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `SchoolsPlayers`
--

DROP TABLE IF EXISTS `SchoolsPlayers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SchoolsPlayers` (
  `playerID` varchar(9) NOT NULL default '',
  `schoolID` varchar(15) NOT NULL default '',
  `yearMin` int(4) default NULL,
  `yearMax` int(4) default NULL,
  PRIMARY KEY  (`playerID`,`schoolID`),
  KEY `schoolID` (`schoolID`,`yearMin`,`yearMax`),
  KEY `schoolID_2` (`schoolID`,`yearMax`,`yearMin`),
  KEY `playerID` (`playerID`,`yearMin`,`yearMax`),
  KEY `playerID_2` (`playerID`,`yearMax`,`yearMin`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;