CREATE DATABASE  IF NOT EXISTS `learn2day` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `learn2day`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: learn2day
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `alternative`
--

DROP TABLE IF EXISTS `alternative`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alternative` (
  `choice` varchar(45) NOT NULL,
  `correct` tinyint(1) DEFAULT NULL,
  `question_questionID` int NOT NULL,
  PRIMARY KEY (`choice`,`question_questionID`),
  KEY `fk_alternative_question1_idx` (`question_questionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alternative`
--

LOCK TABLES `alternative` WRITE;
/*!40000 ALTER TABLE `alternative` DISABLE KEYS */;
INSERT INTO `alternative` VALUES ('0',0,43),('1 billion',0,43),('1-2 million',0,3),('100-120 million',0,3),('1231231212312',0,62),('123123213213',0,62),('15 million',0,26),('19 million',0,12),('1914-1918',1,10),('1915-1919',0,10),('1917-1918',0,25),('1917-1919',0,25),('1917-1921',0,10),('1918-1919',1,25),('1918-1920',0,25),('1920-1924',0,10),('1935-1939',0,1),('1935-1945',0,1),('1939-1945',1,1),('1940-1945',0,1),('1940s',0,47),('1950s',1,47),('1960s',0,47),('1990s',0,47),('2 billion',1,43),('20 million',0,26),('20-30 million',0,3),('21,5 million',1,12),('23,5 million',0,12),('233123',1,62),('25 million',0,12),('25 million',1,26),('28',0,37),('29',1,37),('30',0,37),('30 million',0,26),('3123123',0,62),('40',0,37),('40-50 million',1,3),('Aquaman',0,51),('Atman',1,51),('Burnt',0,50),('China',0,44),('China and\nthe United States',0,29),('Completed',0,50),('Cricket players',0,49),('East India',0,35),('erased',1,50),('False',0,24),('False',0,27),('False',1,28),('False',0,32),('False',1,38),('False',0,39),('False',0,42),('False',0,48),('False',1,53),('France',1,2),('Germany',0,2),('Germany and\nthe Soviet Uniton',0,29),('Germany and \n      China      ',0,29),('God wants more power',0,41),('Italy',0,2),('Japan',0,2),('Jesus got split in half',0,41),('Karma',0,52),('Meat eaters',0,49),('Mission failed we get them next time',0,45),('Moped thieves ',0,49),('Mumbai',0,36),('Muscle cars',0,46),('Napoli',0,36),('New Delhi',0,36),('Nirvana',0,51),('North India',1,35),('North Korea',0,44),('Nuclear weapons',1,46),('one million',0,43),('Operation barbarossa',0,45),('Paulina',0,40),('Pelle',0,40),('Peter',0,40),('Petrus',1,40),('Sabata',0,52),('Samsara',1,52),('Samsung',0,52),('Soul',0,51),('South India',0,35),('Southern Nepal',1,36),('Soviet',0,44),('Space ships',0,46),('Stamped on',0,50),('The 30s',0,30),('The 40s',0,30),('The 50s',1,30),('The 60s',0,30),('The Atlantic Ocean',0,15),('The east didn\'t\naccept the pope',1,41),('The Egyptians',0,31),('The germans started a world war',0,41),('The Greeks',0,31),('The Hebrews',1,31),('The Indian Ocean',0,15),('The Marshall plan',1,45),('The Muslims',0,31),('The Pacific Ocean',1,15),('The rebuildning plan',0,45),('The Southern Ocean',0,15),('The United States and \n    the Soviet Union  ',1,29),('True',1,24),('True',1,27),('True',0,28),('True',1,32),('True',0,38),('True',1,39),('True',1,42),('True',1,48),('True',0,53),('United States of America',1,44),('Vegeterian',1,49),('Water pistols',0,46),('West India',0,35);
/*!40000 ALTER TABLE `alternative` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `courseID` int NOT NULL,
  `course_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`courseID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'test'),(11540,'Religion'),(26917,'123'),(42414,'History'),(53060,'History'),(63497,'Geography');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_has_quiz`
--

DROP TABLE IF EXISTS `course_has_quiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_has_quiz` (
  `course_courseID` int NOT NULL,
  `quiz_name` varchar(45) NOT NULL,
  PRIMARY KEY (`course_courseID`,`quiz_name`),
  KEY `fk_course_has_quiz_quiz1_idx` (`quiz_name`),
  KEY `fk_course_has_quiz_course1_idx` (`course_courseID`),
  CONSTRAINT `fk_course_has_quiz_course1` FOREIGN KEY (`course_courseID`) REFERENCES `course` (`courseID`),
  CONSTRAINT `fk_course_has_quiz_quiz1` FOREIGN KEY (`quiz_name`) REFERENCES `quiz` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_has_quiz`
--

LOCK TABLES `course_has_quiz` WRITE;
/*!40000 ALTER TABLE `course_has_quiz` DISABLE KEYS */;
INSERT INTO `course_has_quiz` VALUES (42414,'World War 2'),(53060,'Continents'),(53060,'World War 1'),(53060,'World War 2');
/*!40000 ALTER TABLE `course_has_quiz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_has_user`
--

DROP TABLE IF EXISTS `course_has_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_has_user` (
  `course_courseID` int NOT NULL,
  `user_username` varchar(45) NOT NULL,
  PRIMARY KEY (`course_courseID`,`user_username`),
  KEY `fk_course_has_user_user1_idx` (`user_username`),
  KEY `fk_course_has_user_course1_idx` (`course_courseID`),
  CONSTRAINT `fk_course_has_user_course1` FOREIGN KEY (`course_courseID`) REFERENCES `course` (`courseID`),
  CONSTRAINT `fk_course_has_user_user1` FOREIGN KEY (`user_username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_has_user`
--

LOCK TABLES `course_has_user` WRITE;
/*!40000 ALTER TABLE `course_has_user` DISABLE KEYS */;
INSERT INTO `course_has_user` VALUES (1,'Simon'),(26917,'Hpmanen'),(53060,'Hpmanen'),(53060,'Simon');
/*!40000 ALTER TABLE `course_has_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `question` varchar(255) NOT NULL,
  `quiz_name` varchar(45) NOT NULL,
  `questionID` int NOT NULL AUTO_INCREMENT,
  `question_type` enum('TF','MC') DEFAULT NULL,
  PRIMARY KEY (`questionID`),
  UNIQUE KEY `question_UNIQUE` (`question`),
  KEY `fk_question_quiz1_idx` (`quiz_name`),
  CONSTRAINT `fk_question_quiz1` FOREIGN KEY (`quiz_name`) REFERENCES `quiz` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES ('When did World War II happen?','World War 2',1,'MC'),('Which of these countries were NOT part of the Axis powers?','World War 2',2,'MC'),('How many people were killed during World War II?','World War 2',3,'MC'),('When did World War I happen?','World War 1',10,'MC'),('How many died in total during World War I?','World War 1',12,'MC'),('When was the Spanish influenza pandemic?','Spanish Flu',25,'MC'),('How many died from the flu?','Spanish Flu',26,'MC'),('The virus spread through the air','Spanish Flu',27,'TF'),('It is called the Cold War because many soldiers froze to death','Cold War',28,'TF'),('Between which two countries was the main conflict?','Cold War',29,'MC'),('During which decade did the Cold War have its peak?','Cold War',30,'MC'),('Who founded Judaism?','Judaism',31,'MC'),('Judiasm is a monotheistic religion','Judaism',32,'TF'),('Where did Buddhism orginate from?','Buddhism',35,'MC'),('Where was Buddah born?','Buddhism',36,'MC'),('How old was buddah when he saw the missary of the world?','Buddhism',37,'MC'),('Did buddah spread his message for 80 years?','Buddhism',38,'TF'),('Does karma mean that if you do something bad it will strike you back.','Buddhism',39,'TF'),('Who was among the first to spread Jesus message?','Christianity',40,'MC'),('What was one of the reasons for the split in Christianity?','Christianity',41,'MC'),('Catholic Church, Orthodox churches and Protestant churches is the biggest christian groups?','Christianity',42,'TF'),('How manny people are christians today?','Christianity',43,'MC'),('Who emerged as the world s most dominant state after World War II?','Cold War',44,'MC'),('What was the name of the economic loan and assistance initiative aimed at rebuilding wartorn Europe?','Cold War',45,'MC'),('What was the most important component of the armaments programs of both the US and the Soviet Union?','Cold War',46,'MC'),('When did the great power rivalry expand into space?','Cold War',47,'MC'),('Was Gorbachev the last soviet president to end the cold war?','Cold War',48,'TF'),('What are many Hindus?','Hinduism',49,'MC'),('What must all karma be done to prevent being resurrected?','Hinduism',50,'MC'),('What is the spiritual inside us known as in Hinduism?','Hinduism',51,'MC'),('What is the ultimate aim of a Hindu to break free from?','Hinduism',52,'MC'),('Do Hindus attempt to convert others to their faith?','Hinduism',53,'TF'),('3231','World War 2',62,'MC');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quiz`
--

DROP TABLE IF EXISTS `quiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quiz` (
  `name` varchar(45) NOT NULL,
  `text` text,
  `topic_name` varchar(45) NOT NULL,
  PRIMARY KEY (`name`),
  KEY `fk_quiz_topic1_idx` (`topic_name`),
  CONSTRAINT `fk_quiz_topic1` FOREIGN KEY (`topic_name`) REFERENCES `topic` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz`
--

LOCK TABLES `quiz` WRITE;
/*!40000 ALTER TABLE `quiz` DISABLE KEYS */;
INSERT INTO `quiz` VALUES ('Buddhism','Buddhism is a religion and philosophy centered on the teachings of the Buddha. It is a big religion which is mostly popular in Asian countries. It started in the late 6th century in notheastern India, but it was not until the beginning of the 1900s that it started spreading to the western countries as well.\n\nThe popular figure who is most known as Buddha today was a prince with the name Siddharta Gautama, Siddharta was born in what is now southern Nepal, on the northern edge of the Ganges River basin, on the outskirts of the ancient civilization of North India.\n\nBuddha lived a big part of his life protected from all evil things in the world such as (illness, death, starvation and such) It was not until the age of 29, Siddhartha first witnessed the misery of the world. After that he made the decision to give up his money to his family and live as an ascetic. He studied meditation with several teachers for the next six years before embarking on a life of deep self-mortification with five companions.\n\nSiddhartha spent 45 years spreading his message across northeastern India, establishing monastic and nunical orders, and receiving the support of kings and merchants. He became critically ill and died at the age of 80.\n\nThe concept of karma is associated with Buddhism, Karma translates to “act” or “deed\'\' with other words. The basic meaning of the concept is, if you do good things, good things will happen to you. If you do bad things, bad things will happen to you. \n\nThe goal of the Buddhist is to reach Nirvana which is related to the concept of Karma. You reach Nirvana by doing good things in your life and when you reach Nirvana you have been released from rebirths and can rest in peace. \n','Religion'),('Christianity','Christianity is a major religion that is centered around the image of Jesus Christ and it originated in the first century in Judea (modern-day Israel and Palestine).\n\nChristianity has over 2 billion followers all around the globe and is the biggest Religion in the world. Catholic, Protestant and Eastern Orthodox are the three main branches of Christianity. There are several more branches but these are the biggest ones.\n\n Around two thousand years ago, Christianity began. The city was dominated by the Romans at the time, and the Jewish people awaited the arrival of the Messiah to save them. \n\nThe Jews saw the Messiah as a human being who would be sent by God to bring peace to the Jewish people.\n\nPeter, Jesus\' successor, was among the first to spread Jesus\' message. The great spread, however, did not occur until the 380s, when Christianity was declared the Roman Empire\'s official religion.\n\nThe Roman Empire was split into two parts in 395, the Western Roman Empire and the Eastern Roman Empire. The Orthodox Church arose from the eastern part, while the Catholic Church arose from the western part (the general church). The Christians in the East refused to accept the pope as the leader of the christian people which led to the split.\n\n\n\n\n','Religion'),('Cold War','The Cold War was a struggle between the US and large parts of the Western world on the one hand, and the Soviet Union and its allies, mostly in Eastern Europe, on the other. The conflict lasted from 1945 to 1991 and was characterized as a latent war in which the possibility of war was still present but never materialized into a full-fledged conflict between the two sides. The two power blocs, on the other hand, fought militarily against each other indirectly through agents and puppets such as other states and guerrilla groups that received financial and military support. The Cold War was also a propaganda war in which the sides tried to outshine one another and glorify their own ideological regimes while blackmailing their adversaries. This was evident in the sports world, especially in relation to the Olympics. \n\nThe ideological contradictions between the great powers were the guiding force behind the Cold War: the United States and the Western world stood for a market-liberal economy and democratic social structure, while the Soviet Union retained a state-controlled planned economy and dictatorship. When the Allied powers met in 1945 at the Yalta and Potsdam conferences to prepare for Europe\'s post-World War II future, the great gaps in the political and economic spheres became apparent. \n\nThe United States emerged as the world\'s most dominant state, both militarily and economically, following World War II. The Soviet Union, on the other hand, had developed a large military presence in Central and Eastern Europe, which it was determined to consolidate and strengthen. \n\nThe United States reacted by establishing the so-called Truman Doctrine, which was essentially a line of action aimed at assisting foreign governments threatened by communism in order to counter the emerging communist threat in Europe. The Marshall Plan, an economic loan and assistance initiative aimed at rebuilding war-torn Europe, was part of the United States\' new strategy. The US\'s new mission was to establish economic and political stability in Western Europe in order to integrate it into the market system and capitalist sphere while still promoting democracy. \n\nGermany, especially Berlin, became a focal point between the great powers at the end of the war, when it was split into two halves: West Berlin, governed by the Western powers, and East Berlin, controlled by the Soviet Union. \n\nThe Cold War grew and spread from Europe to Asia in the late 1940s. As a result, the dispute spread across the globe. \n\nThe United States and the United Nations confronted North Korea and China during the Korean War. As a result of this, the arms race began in earnest. The two superpowers\' military expenses have now skyrocketed. Nuclear weapons were the most important component of the armaments programs of both the US and the Soviet Union. \n\nThe intensified arms race between the power blocs established a kind of terror equilibrium, in which both sides\' nuclear weapons stopped them from fighting each other because they would annihilate themselves if they did. \n\nThe most distinguishing characteristics of the Cold War will now be the balance of violence and the arms race. Both power blocs amassed sufficient nuclear weapons to wipe out the entire planet.\n\n During the late 1950s, the great power rivalry expanded into space. As a result, the arms race morphed into a space race, in which the two superpowers competed for technological advancements in space in order to boost their respective countries\' prestige. \n\nGorbachev was the last soviet president and was the man to end the cold war. Gorbachev\'s liberal policies paved the way for progressive ideals to spread. Gorbachev\'s policies led to a democratic transition in Eastern Europe, which culminated in the collapse of the Berlin Wall, which had tremendous consequences for the Soviet Union, Eastern Europe, and the rest of the world.','History'),('Continents','There are seven continents in the world and these are: Africa, Antarctica, Asia, Australia/Oceania, Europe, North America, and South America.','Geography'),('Hinduism','Hinduism does not have a founder, it is based on a flow of wisdom from several different religions that has been melded into one religion over time. Because of the tolerance and dynamism of Hindu teaching against other faiths, it has survived both conquests and rival religious teachings over time. Unlike many other religions, Hindus do not attempt to convert others to their faith.\n\nGods of Hinduism are more like humans in contrast to the abstract God of the monotheistic religions. There is a cyclical view of time in Hinduism. As a result, there is no beginning or end. \n\nAccording to Hindu belief, the earth, like all life, is continuously created, destroyed, and resurrected. All life is a part of an endless cycle of birth, death, and rebirth known as samsara. \n\nThe ultimate goal of a Hindu is to break free from samsara. After that, you can be rescued. However, the path there can be many lifetimes long. God and the world are one and the same for Hindus. All is guided by the world’s soul which the Hindus call Braham. As a result, God is present in ourselves as a spirit called Atman.\n\nThe law of karma is a cause-and-effect law that decides whether a person will be reborn higher or lower in the social hierarchy in the next life. As a result, it is important to accumulate as much good karma as possible in life by being a good person. Hinduism attempts to break free from samsara. All karma must be erased in order to break the samsara cycle.\n\nAll life is sacred to a Hindu. As part of Brahman, all aspects of life have meaning. As a result, many Hindus are vegetarians. Cows are particularly revered because they provide so much to society. \n\n\n\n\n\n\n','Religion'),('Judaism','The ancient Hebrews founded Judaism, a monotheistic religion. Judaism is described by a belief in a single transcendent God who revealed himself to Abraham, Moses, and the Hebrew prophets, as well as a religious life based on the Bible and rabbinic traditions. Judaism is a complex process that involves religion, law, and numerous cultural practices for the Jewish people.','Religion'),('Oceans','The Seven Seas include the Arctic, North Atlantic, South Atlantic, North Pacific, South Pacific, Indian, and Southern Oceans.','Geography'),('Spanish Flu','Spanish flu was the most serious influenza epidemic of the twentieth century and one of the deadliest viruses in human history in terms of deaths caused by the virus. It lasted from 1918 to 1919. Influenza is caused by a virus that is spread from person to person through respiratory secretions in the air. If a new strain of influenza virus evolves from which the population has no immunity, an epidemic may occur. ','History'),('World War 1','World War I was an international conflict that involved the majority of Europe, as well as the United States, the Middle East, and other countries from around the globe. The war kept going from 1914 to 1918. This war is called the first world war since it was the first time countries from all over the world were involved in the same war. The main parties of the war were, The Central Powers which primarily consisted of Germany, Austria-Hungary, and Turkey were up against the Allies who primarily consisted of France, the United Kingdom, Russia, Italy, Japan, and starting 1917 joined by the United States. In 1918 the Central Powers surrendered and the war came to an end. In terms of the amount of deaths there were about 20 million deaths which was practically unprecedented until World War II came along.\n\n','History'),('World War 2','World War II was a conflict that engulfed nearly every country on the planet. It began in 1939 and ended in 1945. It\'s called a world war because countries from all over the world were involved. The main parties in the war were The Axis powers which included: Germany, Italy, and Japan who were against the Allies which included: France, the United Kingdom, the United States, and the Soviet Union. In several ways, the war was a continuation of the conflicts left unresolved by World War I, following a tense 20-year hiatus. World War II was the largest war with more deaths than any other war in history so far, with 40–50 million people killed.','History');
/*!40000 ALTER TABLE `quiz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topic` (
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
INSERT INTO `topic` VALUES ('Geography'),('History'),('Religion');
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `Firstname` varchar(45) DEFAULT NULL,
  `Lastname` varchar(45) DEFAULT NULL,
  `Teacher` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('123','123','123','123',0),('Hampus','Nilsson','Hampus','Nilsson',1),('HimeTeacher','himehime','Hampus','Nilsson',1),('Hpmanen','himehime','Hampus','Nilsson',1),('Learn2day','lol','Hampus','Nilsson',1),('lol','lol','lol','lol',1),('Simon','simon','Simon','Wikström',1),('Student','test','Student','Student',0),('test','test','Hime','CEO',1),('yay','yay','testing','stuff',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_does_quiz`
--

DROP TABLE IF EXISTS `user_does_quiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_does_quiz` (
  `user_username` varchar(45) NOT NULL,
  `quiz_name` varchar(45) NOT NULL,
  `score` int DEFAULT NULL,
  PRIMARY KEY (`user_username`,`quiz_name`),
  KEY `fk_user_has_quiz_quiz1_idx` (`quiz_name`),
  KEY `fk_user_has_quiz_user1_idx` (`user_username`),
  CONSTRAINT `fk_user_has_quiz_quiz1` FOREIGN KEY (`quiz_name`) REFERENCES `quiz` (`name`),
  CONSTRAINT `fk_user_has_quiz_user1` FOREIGN KEY (`user_username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_does_quiz`
--

LOCK TABLES `user_does_quiz` WRITE;
/*!40000 ALTER TABLE `user_does_quiz` DISABLE KEYS */;
INSERT INTO `user_does_quiz` VALUES ('Hpmanen','Buddhism',1),('Hpmanen','Continents',7),('Hpmanen','Spanish Flu',2),('Hpmanen','World War 1',1),('Hpmanen','World War 2',3),('Simon','World War 2',1);
/*!40000 ALTER TABLE `user_does_quiz` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-27 11:15:34
