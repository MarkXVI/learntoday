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
INSERT INTO `alternative` VALUES ('     China and     \nthe United States',0,29),('   Germany and\nthe Soviet Uniton',0,29),('0',0,43),('1 billion',0,43),('1-2 million',0,3),('100-120 million',0,3),('15 million',0,26),('19 million',0,12),('1914-1918',1,10),('1915-1919',0,10),('1917-1918',0,25),('1917-1919',0,25),('1917-1921',0,10),('1918-1919',1,25),('1918-1920',0,25),('1920-1924',0,10),('1935-1939',0,1),('1935-1945',0,1),('1939-1945',1,1),('1940-1945',0,1),('1940s',0,47),('1950s',1,47),('1960s',0,47),('1990s',0,47),('2 billion',1,43),('20 million',0,26),('20-30 million',0,3),('21,5 million',1,12),('23,5 million',0,12),('25 million',0,12),('25 million',1,26),('28',0,37),('29',1,37),('30',0,37),('30 million',0,26),('40',0,37),('40-50 million',1,3),('Aquaman',0,51),('Atman',1,51),('Burnt',0,50),('China',0,44),('Completed',0,50),('Cricket players',0,49),('East India',0,35),('erased',1,50),('False',0,24),('False',0,27),('False',1,28),('False',0,32),('False',1,38),('False',0,39),('False',0,42),('False',0,48),('False',1,53),('France',1,2),('Germany',0,2),('Germany and \n      China      ',0,29),('God wants more power',0,41),('Italy',0,2),('Japan',0,2),('Jesus got split in half',0,41),('Karma',0,52),('Meat eaters',0,49),('Mission failed we get them next time',0,45),('Moped thieves ',0,49),('Mumbai',0,36),('Muscle cars',0,46),('Napoli',0,36),('New Delhi',0,36),('Nirvana',0,51),('North India',1,35),('North Korea',0,44),('Nuclear weapons',1,46),('one million',0,43),('Operation barbarossa',0,45),('Paulina',0,40),('Pelle',0,40),('Peter',0,40),('Petrus',1,40),('Sabata',0,52),('Samsara',1,52),('Samsung',0,52),('Soul',0,51),('South India',0,35),('Southern Nepal',1,36),('Soviet',0,44),('Space ships',0,46),('Stamped on',0,50),('The 30s',0,30),('The 40s',0,30),('The 50s',1,30),('The 60s',0,30),('The Atlantic Ocean',0,15),('The Egyptians',0,31),('The germans started a world war',0,41),('The Greeks',0,31),('The Hebrews',1,31),('The Indian Ocean',0,15),('The Marshall plan',1,45),('The Muslims',0,31),('The Pacific Ocean',1,15),('The rebuildning plan',0,45),('The Southern Ocean',0,15),('The United States and \n    the Soviet Union  ',1,29),('The west didnt like the pop',1,41),('True',1,24),('True',1,27),('True',0,28),('True',1,32),('True',0,38),('True',1,39),('True',1,42),('True',1,48),('True',0,53),('United States of America',1,44),('Vegeterian',1,49),('Water pistols',0,46),('West India',0,35);
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
  PRIMARY KEY (`courseID`),
  UNIQUE KEY `course_name_UNIQUE` (`course_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (63497,'Geography'),(53060,'History'),(11540,'Religion');
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
INSERT INTO `course_has_user` VALUES (53060,'Hpmanen'),(53060,'Simon');
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
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES ('When did World War II happen?','World War 2',1,'MC'),('Which of these countries were NOT part of the Axis powers?','World War 2',2,'MC'),('How many people were killed during World War II?','World War 2',3,'MC'),('When did World War I happen?','World War 1',10,'MC'),('How many died in total during World War I?','World War 1',12,'MC'),('When was the Spanish influenza pandemic?','Spanish Flu',25,'MC'),('How many died from the flu?','Spanish Flu',26,'MC'),('The virus spread through the air','Spanish Flu',27,'TF'),('It is called the Cold War because many soldiers froze to death','Cold War',28,'TF'),('Between which two countries was the main conflict?','Cold War',29,'MC'),('During which decade did the Cold War have its peak?','Cold War',30,'MC'),('Who founded Judaism?','Judaism',31,'MC'),('Judiasm is a monotheistic religion','Judaism',32,'TF'),('WHo is jesus','Christianity',33,'MC'),('Where did Buddhism orginate from?','Buddhism',35,'MC'),('Where was Buddah born?','Buddhism',36,'MC'),('How old was buddah when he saw the missary of the world?','Buddhism',37,'MC'),('Did buddah spread his message for 80 years?','Buddhism',38,'TF'),('Does karma mean that if you do something bad it will strike you back.','Buddhism',39,'TF'),('Who was among the first to spread Jesus message?','Christianity',40,'MC'),('What was one of the reasons for the split in Christianity?','Christianity',41,'MC'),('Catholic Church, Orthodox churches and Protestant churches is the biggest christian groups?','Christianity',42,'TF'),('How manny people are christians today?','Christianity',43,'MC'),('Who emerged as the world s most dominant state after World War II?','Cold War',44,'MC'),('What was the name of the economic loan and assistance initiative aimed at rebuilding wartorn Europe?','Cold War',45,'MC'),('What was the most important component of the armaments programs of both the US and the Soviet Union?','Cold War',46,'MC'),('When did the great power rivalry expand into space?','Cold War',47,'MC'),('Was Gorbachev the last soviet president to end the cold war?','Cold War',48,'TF'),('What are many Hindus?','Hinduism',49,'MC'),('What must all karma be done to prevent being resurrected?','Hinduism',50,'MC'),('What is the spiritual inside us known as in Hinduism?','Hinduism',51,'MC'),('What is the ultimate aim of a Hindu to break free from?','Hinduism',52,'MC'),('Do Hindus attempt to convert others to their faith?','Hinduism',53,'TF');
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
INSERT INTO `quiz` VALUES ('Buddhism','Buddhism is a religion and philosophy centered on the teachings of the Buddha (Sanskrit: \"Awakened One\"), a teacher who lived in northern India between the mid-sixth and mid-fourth centuries BCE (before the Common Era). It is a big religion spreading from  India to Central and Southeast Asia, China, Korea, and Japan. In the beggining of the 20th century it spred to the west.\n\nBuddhism originated in northeastern India between the late 6th and early 4th centuries BCE, during a period of rapid social change and religious activity.\n\nBuddhism, like many of the sects that arose in northeastern India at the time had a special pattern called the triratna—the \"Three Jewels\" of Buddha (the teacher), dharma (the teaching), and sangha (the community) (the community).\n\nThe historical figure known as the Buddha (whose life is largely based on legend) was born in what is now southern Nepal, on the northern edge of the Ganges River basin, on the outskirts of the ancient civilization of North India.\n\nThe majority of information about his life comes from Buddhist scriptures, the oldest of which were written shortly before the Common Age began, and therefore some centuries after his death. His early years were spent in luxury and comfort, and his father shielded him from the world\'s ills, such as old age, illness, and death.  At the age of 29, the prince had a profound experience when he first witnessed the misery of the world. He made the decision to give up his money to family and live as an ascetic. He studied meditation with several teachers for the next six years before embarking on a life of deep self-mortification with five companions.\n\nHe fainted from weakness one day when swimming in a river and thus concluded that mortification was not the road to suffering relief. The prince gave up his life of strict asceticism and sat in meditation under a tree, receiving enlightenment, which is often associated with knowing the Four Noble Truths. The Buddha spent the next 45 years spreading his message across northeastern India, establishing monastic and nunical orders, and receiving the support of kings and merchants. He became critically ill and died at the age of 80.\n\nThe five aggregates or constituents (khandhas) of human life are defined by Buddhists as follows: (1) corporeality or physical forms (rupa), (2) feelings or sensations (vedana), (3) ideations (sanna), (4) mental formations or dispositions (sankhara), and (5) consciousness (vinnana).\n\nIn pre-Buddhist India, the doctrine of karma (Sanskrit: karman; literally \"act\" or \"deed\") was already connected with the belief in rebirth, or samsara, as a potentially infinite sequence of worldly existences in which every being is caught up, and it was embraced by nearly all Buddhist traditions.According to the doctrine, good deeds produce a positive and happy outcome, as well as a tendency toward more good deeds, while bad deeds produce an evil result and a tendency toward more evil deeds. ','Religion'),('Christianity','Christianity is a major religion that originated in the first century CE with the life, teachings, and death of Jesus of Nazareth (the Christ, or the Anointed One of God). It has grown to be the world\'s largest religion and the most geographically dispersed of all faiths. It has a global following of over two billion people. The Roman Catholic Church, Eastern Orthodox churches, and Protestant churches are the three main denominations. \n\nChristianity is a religious belief centered around the image of Jesus Christ at the most fundamental level. Faith refers to both the believers\' act of trust and the substance of their faith in this sense. Christianity is more than a religious belief system as a tradition. Since Jesus first became the object of faith, it has also created a community, a collection of ideas and ways of life, rituals, and objects that have been passed down from generation to generation. As a result, Christianity is both a living religious tradition and the society that it leaves behind. The church, or the group of people who make up the body of believers, is the agent of Christianity.\n\n Around two thousand years ago, Christianity began in Judea (modern-day Israel and Palestine). The city was dominated by the Romans at the time, and the Jewish people awaited the arrival of the Messiah to save them. \n\nThe Jews saw the Messiah as a good ruler who had been sent by God to bring peace and prosperity to the Jewish people. However, the Messiah will not be divine, but rather a regular human being. The Messiah will save Judea from Roman opression with God\'s aid. \n\nJesus was arrested by local law enforcement when he was just over 30 years old, sentenced to death as a rebel, and crucified (crucifixion was at this time a common method of execution in the Roman Empire). \n\nMost Jews forgot about Jesus, but some of his followers believed he was God\'s messenger. They believed Jesus had risen from the dead and gone to be with God in heaven. He was about to return to Earth to become the world\'s Savior.\n\nBy the early 300s, about 15% of the Roman Empire\'s population had converted to Christianity. Peter, Jesus\' successor, was among the first to spread Jesus\' message. The great spread, however, did not occur until the 380s, when Christianity was declared the Roman Empire\'s official religion. Christians no longer had to meet in secret, and they could begin to establish churches. \n\nThe Roman Empire was split into two parts in 395: the Western Roman Empire and the Eastern Roman Empire. The Orthodox Church arose from the eastern part, while the Catholic Church arose from the western part (the general church). The refusal of Christians in the East to accept the bishop of Rome as pope and chief of all Christians was one of the reasons for the split. The next big split in Christianity happened later in the 16th century, during the so-called Reformation, when the Protestant Church seceded from the Catholic Church.','Religion'),('Cold War','The Cold War was a struggle between the US and large parts of the Western world on the one hand, and the Soviet Union and its allies, mostly in Eastern Europe, on the other. The conflict lasted from 1945 to 1991 and was characterized as a latent war in which the possibility of war was still present but never materialized into a full-fledged conflict between the two sides. The two power blocs, on the other hand, fought militarily against each other indirectly through agents and puppets such as other states and guerrilla groups that received financial and military support. The Cold War was also a propaganda war in which the sides tried to outshine one another and glorify their own ideological regimes while blackmailing their adversaries. This was evident in the sports world, especially in relation to the Olympics. \n\nThe ideological contradictions between the great powers were the guiding force behind the Cold War: the United States and the Western world stood for a market-liberal economy and democratic social structure, while the Soviet Union retained a state-controlled planned economy and dictatorship. When the Allied powers met in 1945 at the Yalta and Potsdam conferences to prepare for Europe\'s post-World War II future, the great gaps in the political and economic spheres became apparent. \n\nThe United States emerged as the world\'s most dominant state, both militarily and economically, following World War II. The Soviet Union, on the other hand, had developed a large military presence in Central and Eastern Europe, which it was determined to consolidate and strengthen. \n\nThe United States reacted by establishing the so-called Truman Doctrine, which was essentially a line of action aimed at assisting foreign governments threatened by communism in order to counter the emerging communist threat in Europe. The Marshall Plan, an economic loan and assistance initiative aimed at rebuilding war-torn Europe, was part of the United States\' new strategy. The US\'s new mission was to establish economic and political stability in Western Europe in order to integrate it into the market system and capitalist sphere while still promoting democracy. \n\nGermany, especially Berlin, became a focal point between the great powers at the end of the war, when it was split into two halves: West Berlin, governed by the Western powers, and East Berlin, controlled by the Soviet Union. \n\nThe Cold War grew and spread from Europe to Asia in the late 1940s. As a result, the dispute spread across the globe. \n\nThe United States and the United Nations confronted North Korea and China during the Korean War. As a result of this, the arms race began in earnest. The two superpowers\' military expenses have now skyrocketed. Nuclear weapons were the most important component of the armaments programs of both the US and the Soviet Union. \n\nThe intensified arms race between the power blocs established a kind of terror equilibrium, in which both sides\' nuclear weapons stopped them from fighting each other because they would annihilate themselves if they did. \n\nThe most distinguishing characteristics of the Cold War will now be the balance of violence and the arms race. Both power blocs amassed sufficient nuclear weapons to wipe out the entire planet.\n\n During the late 1950s, the great power rivalry expanded into space. As a result, the arms race morphed into a space race, in which the two superpowers competed for technological advancements in space in order to boost their respective countries\' prestige. \n\nGorbachev was the last soviet president and was the man to end the cold war. Gorbachev\'s liberal policies paved the way for progressive ideals to spread. Gorbachev\'s policies led to a democratic transition in Eastern Europe, which culminated in the collapse of the Berlin Wall, which had tremendous consequences for the Soviet Union, Eastern Europe, and the rest of the world.','History'),('Continents','There are seven continents in the world and these are: Africa, Antarctica, Asia, Australia/Oceania, Europe, North America, and South America.','Geography'),('Hinduism','Hinduism does not have a founder; rather, it is based on a flow of wisdom from several different religions that has been melded into a flexible doctrine over time. The substance of the doctrine has been built on and modified throughout history, owing to influences such as Greek mythology, Persian faith, Christianity, and Islam. Because of the tolerance and dynamism of Hindu teaching against other faiths, it has survived both conquests and rival religious teachings over time.Hindus, unlike Christians and Muslims, do not attempt to convert others to their faith. As a result, the \"modern\" Indian religions have been able to coexist peacefully with Hinduism.\n\nThe word Hindu comes from a geographical term that refers to the people who live near the Indus River. However, the term Hinduism is now used to refer to a wide range of religious views and beliefs held by a significant portion of India\'s population.There are many gods in Hinduism. Many Hindus pray to the god or gods who are most appropriate for daily use. As a result, the majority of people worship many gods. In Hinduism, therefore, the distance between God and man is not as good as in Judaism, Christianity and Islam because the gods of Hinduism are more like humans in contrast to the abstract God of the monotheistic religions. Food and flowers are often sacrificed to the gods in conjunction with prayer, and the gods bless the food and look after the worshiper\'s needs in other respects. Both gods have their own unique feasts to honor them. \n\nThere is a cyclical view of time in Hinduism. As a result, there is no beginning or end. According to Hindu belief, the earth, like all life, is continuously created, destroyed, and resurrected. All life is a part of an endless cycle of birth, death, and rebirth known as samsara. The ultimate aim of a Hindu is to break free from samsara, the endless cycle of rebirth. After that, and only then, can you be rescued. However, the path there is long and winding, passing through several lives of good deeds, or karma. God and the world are one and the same for Hindus. All is pervaded by Brahman (the world\'s soul). As a result, God is present in both ourselves and all other living things. The spiritual inside us is known as the Atman, and it is similar to our spirit. \n\nThe law of karma is a cause-and-effect law that decides whether a person will be reborn higher or lower in the social hierarchy in the next life. As a result, it is important to accumulate as much good karma as possible in life by adhering to society\'s predetermined laws. Hinduism attempts to break free from the period of rebirth known as samsara. Only then will moksha, or redemption, be attained. Being resurrected has a negative connotation. All karma must be erased in order to prevent being resurrected. Samsara can be compared to a fire in which karma is the fuel. All life is sacred to a Hindu. As part of Brahman, all aspects of life, including man, have meaning. As a result, many Hindus are vegetarians. Cows are particularly revered because they provide so much to society. Cows produce milk and other dairy products, for example.','Religion'),('Judaism','The ancient Hebrews founded Judaism, a monotheistic religion. Judaism is described by a belief in a single transcendent God who revealed himself to Abraham, Moses, and the Hebrew prophets, as well as a religious life based on the Bible and rabbinic traditions. Judaism is a complex process that involves religion, law, and numerous cultural practices for the Jewish people.','Religion'),('Oceans','The Seven Seas include the Arctic, North Atlantic, South Atlantic, North Pacific, South Pacific, Indian, and Southern Oceans.','Geography'),('Spanish Flu','The 1918–19 influenza pandemic, also known as the Spanish influenza pandemic or Spanish flu, was the most serious influenza epidemic of the twentieth century and one of the most deadly pandemics in human history in terms of total deaths. Influenza is caused by a virus that is spread from person to person through respiratory secretions in the air. If a new strain of influenza virus evolves from which the population has no immunity, an epidemic may occur. \n\nThe cause of the pandemic\'s high mortality is now understood to be an influenza virus known as influenza type A subtype H1N1, which killed an estimated 25 million people, though some experts believe it killed as many as 40–50 million. ','History'),('World War 1','World War I, also known as the First World War or the Great War, was an international conflict that involved much of Europe, as well as Russia, the United States, the Middle East, and other countries, from 1914 to 1918. The Central Powers—primarily Germany, Austria-Hungary, and Turkey—were pitted against the Allies—primarily France, the United Kingdom, Russia, Italy, Japan, and, starting in 1917, the United States. The Central Powers were defeated at the end of the war. In terms of slaughter, bloodshed, and devastation, the war was practically unprecedented. \n\nDuring World War I, approximately 8,500,000 soldiers died as a result of wounds or disease. A total of 13,000,000 civilians might have died as a result of the war. Because of the new technology and combat styles used in the war, the number of casualties was much greater than in any previous war.','History'),('World War 2','During the years 1939–45, World War II, also known as the Second World War, was a conflict that engulfed nearly every country on the planet. The Axis powers—Germany, Italy, and Japan—fought against the Allies—France, the United Kingdom, the United States, and the Soviet Union. In several ways, the war was a continuation of the conflicts left unresolved by World War I, following a tense 20-year hiatus. World War II was the bloodiest and largest war in history, with 40–50 million people killed. To be continued.....jkjkljl','History');
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
INSERT INTO `user_does_quiz` VALUES ('Hpmanen','World War 2',3),('Simon','World War 2',1);
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

-- Dump completed on 2021-05-19 16:09:41
