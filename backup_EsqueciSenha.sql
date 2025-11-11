-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           10.4.32-MariaDB - mariadb.org binary distribution
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura do banco de dados para guilhermes_andressa_hospital
DROP DATABASE IF EXISTS `guilhermes_andressa_hospital`;
CREATE DATABASE IF NOT EXISTS `guilhermes_andressa_hospital` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `guilhermes_andressa_hospital`;

-- Copiando estrutura para tabela guilhermes_andressa_hospital.ambulatorio
DROP TABLE IF EXISTS `ambulatorio`;
CREATE TABLE IF NOT EXISTS `ambulatorio` (
  `codAmbulatorio` int(11) NOT NULL AUTO_INCREMENT,
  `horarioAtendimento` time NOT NULL,
  `quantAmbulancias` int(11) DEFAULT NULL,
  `hospitalAmbulatorio` int(11) NOT NULL,
  PRIMARY KEY (`codAmbulatorio`,`hospitalAmbulatorio`) USING BTREE,
  KEY `fk_ambulatorio_hospital1_idx` (`hospitalAmbulatorio`) USING BTREE,
  CONSTRAINT `fk_ambulatorio_hospital1` FOREIGN KEY (`hospitalAmbulatorio`) REFERENCES `hospital` (`codHospital`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Copiando dados para a tabela guilhermes_andressa_hospital.ambulatorio: ~0 rows (aproximadamente)
INSERT INTO `ambulatorio` (`codAmbulatorio`, `horarioAtendimento`, `quantAmbulancias`, `hospitalAmbulatorio`) VALUES
	(1, '06:30:00', 5, 5);

-- Copiando estrutura para tabela guilhermes_andressa_hospital.ambulatorio_has_medicacao
DROP TABLE IF EXISTS `ambulatorio_has_medicacao`;
CREATE TABLE IF NOT EXISTS `ambulatorio_has_medicacao` (
  `codMedicacaoAmbulatorio` int(11) NOT NULL AUTO_INCREMENT,
  `ambulatorio_codAmbulatorio` int(11) NOT NULL,
  `medicacao_codRemedio` int(11) NOT NULL,
  `quantidadeRemedio` varchar(250) NOT NULL,
  PRIMARY KEY (`codMedicacaoAmbulatorio`,`ambulatorio_codAmbulatorio`,`medicacao_codRemedio`) USING BTREE,
  KEY `fk_ambulatorio_has_medicacao_medicacao1_idx` (`medicacao_codRemedio`) USING BTREE,
  KEY `fk_ambulatorio_has_medicacao_ambulatorio1_idx` (`ambulatorio_codAmbulatorio`) USING BTREE,
  CONSTRAINT `fk_ambulatorio_has_medicacao_ambulatorio1` FOREIGN KEY (`ambulatorio_codAmbulatorio`) REFERENCES `ambulatorio` (`codAmbulatorio`),
  CONSTRAINT `fk_ambulatorio_has_medicacao_medicacao1` FOREIGN KEY (`medicacao_codRemedio`) REFERENCES `medicacao` (`codRemedio`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Copiando dados para a tabela guilhermes_andressa_hospital.ambulatorio_has_medicacao: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela guilhermes_andressa_hospital.auditoria
DROP TABLE IF EXISTS `auditoria`;
CREATE TABLE IF NOT EXISTS `auditoria` (
  `codAuditoria` int(11) NOT NULL AUTO_INCREMENT,
  `acao` varchar(600) NOT NULL,
  `tabela` varchar(30) NOT NULL,
  `dataHora` datetime NOT NULL,
  `usuario` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`codAuditoria`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Copiando dados para a tabela guilhermes_andressa_hospital.auditoria: ~10 rows (aproximadamente)
INSERT INTO `auditoria` (`codAuditoria`, `acao`, `tabela`, `dataHora`, `usuario`) VALUES
	(1, 'Atualização na quantidade de ambulancias4', 'ambulatorio', '2024-12-10 22:45:37', 'root@localhost'),
	(2, 'Atualização na quantidade de ambulancias do código = 3', 'ambulatorio', '2024-12-10 22:50:52', 'root@localhost'),
	(3, 'Atualização na quantidade de ambulancias do código = 4', 'ambulatorio', '2024-12-10 22:51:59', 'root@localhost'),
	(4, 'Atualização na quantidade de ambulancias do código = 4', 'ambulatorio', '2024-12-10 22:55:48', 'root@localhost'),
	(5, 'Atualização na quantidade de ambulancias do código = 3', 'ambulatorio', '2024-12-10 22:56:29', 'root@localhost'),
	(6, 'Salário do cargo = Auxiliar atualizado para = 2.2250738585072014e-308', 'cargo', '2025-04-14 20:41:26', 'root@localhost'),
	(7, 'Salário do cargo = Auxiliar atualizado para = 2.2250738585072014e-308', 'cargo', '2025-04-14 20:44:50', 'root@localhost'),
	(8, 'Salário do cargo = Auxiliar atualizado para = 2100', 'cargo', '2025-04-14 20:57:42', 'root@localhost'),
	(9, 'Valor do remédio = Amoxicilina atualizado para = 21.9 devido a descontos!', 'medicacao', '2025-06-24 13:49:12', 'root@localhost'),
	(10, 'Valor do remédio = Amoxicilina atualizado para = 21.9 devido a descontos!', 'medicacao', '2025-07-15 14:38:19', 'root@localhost');

-- Copiando estrutura para tabela guilhermes_andressa_hospital.cargo
DROP TABLE IF EXISTS `cargo`;
CREATE TABLE IF NOT EXISTS `cargo` (
  `codCargo` int(11) NOT NULL AUTO_INCREMENT,
  `nomeCargo` varchar(250) NOT NULL,
  `salarioInicial` double NOT NULL,
  `descricao` varchar(250) DEFAULT NULL,
  `bonificacao` double DEFAULT NULL,
  PRIMARY KEY (`codCargo`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Copiando dados para a tabela guilhermes_andressa_hospital.cargo: ~5 rows (aproximadamente)
INSERT INTO `cargo` (`codCargo`, `nomeCargo`, `salarioInicial`, `descricao`, `bonificacao`) VALUES
	(1, 'Médico(a)', 7500, 'Dermatologista, vascular e pediatra', NULL),
	(2, 'Enfermeiro(a)', 3987.9, 'Enfermeira especialista em internações!', NULL),
	(3, 'Secretário(a) especializado(a)', 3400, 'Secretários(as) de finanças qualificados(as)!', NULL),
	(6, 'Cozinheiro(a)', 1750, 'Especialista na alimentação e cuidado dos nossos pacientes!', NULL),
	(13, 'Auxiliar', 2000, 'ajuda a secretária', 0);

-- Copiando estrutura para tabela guilhermes_andressa_hospital.consulta
DROP TABLE IF EXISTS `consulta`;
CREATE TABLE IF NOT EXISTS `consulta` (
  `codConsulta` int(11) NOT NULL AUTO_INCREMENT,
  `diagnostico` varchar(400) NOT NULL,
  `dataConsulta` date NOT NULL,
  `pacienteConsulta` int(11) NOT NULL,
  `funcionarioConsulta` int(11) NOT NULL,
  PRIMARY KEY (`codConsulta`,`pacienteConsulta`,`funcionarioConsulta`) USING BTREE,
  KEY `fk_consulta_paciente1_idx` (`pacienteConsulta`) USING BTREE,
  KEY `fk_consulta_funcionario1_idx` (`funcionarioConsulta`) USING BTREE,
  CONSTRAINT `fk_consulta_funcionario1` FOREIGN KEY (`funcionarioConsulta`) REFERENCES `funcionario` (`codFunc`),
  CONSTRAINT `fk_consulta_paciente1` FOREIGN KEY (`pacienteConsulta`) REFERENCES `paciente` (`codPaciente`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Copiando dados para a tabela guilhermes_andressa_hospital.consulta: ~2 rows (aproximadamente)
INSERT INTO `consulta` (`codConsulta`, `diagnostico`, `dataConsulta`, `pacienteConsulta`, `funcionarioConsulta`) VALUES
	(3, 'Febre de 38.9 graus Celsius ', '2024-11-05', 10, 2),
	(4, 'Rinite alérgica', '2024-12-09', 10, 1);

-- Copiando estrutura para tabela guilhermes_andressa_hospital.exame
DROP TABLE IF EXISTS `exame`;
CREATE TABLE IF NOT EXISTS `exame` (
  `codExame` int(11) NOT NULL AUTO_INCREMENT,
  `tipoExame` varchar(300) NOT NULL,
  `valor` double NOT NULL,
  `resultado` varchar(300) NOT NULL,
  `pacienteExame` int(11) NOT NULL,
  `funcionarioExame` int(11) NOT NULL,
  PRIMARY KEY (`codExame`,`pacienteExame`,`funcionarioExame`) USING BTREE,
  KEY `fk_exame_paciente1_idx` (`pacienteExame`) USING BTREE,
  KEY `fk_exame_funcionario1_idx` (`funcionarioExame`) USING BTREE,
  CONSTRAINT `fk_exame_funcionario1` FOREIGN KEY (`funcionarioExame`) REFERENCES `funcionario` (`codFunc`),
  CONSTRAINT `fk_exame_paciente1` FOREIGN KEY (`pacienteExame`) REFERENCES `paciente` (`codPaciente`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Copiando dados para a tabela guilhermes_andressa_hospital.exame: ~0 rows (aproximadamente)
INSERT INTO `exame` (`codExame`, `tipoExame`, `valor`, `resultado`, `pacienteExame`, `funcionarioExame`) VALUES
	(1, 'Radiografia da coluna', 150.8, 'Nenhuma anomalia detectada!', 10, 1);

-- Copiando estrutura para tabela guilhermes_andressa_hospital.funcionario
DROP TABLE IF EXISTS `funcionario`;
CREATE TABLE IF NOT EXISTS `funcionario` (
  `codFunc` int(11) NOT NULL AUTO_INCREMENT,
  `nomeFunc` varchar(500) NOT NULL,
  `cpf` varchar(65) NOT NULL,
  `rg` varchar(45) NOT NULL,
  `numRegistro` varchar(200) NOT NULL,
  `dataAdmissao` date NOT NULL,
  `horarioTrabalho` time DEFAULT NULL,
  `cargoFuncionario` int(11) NOT NULL,
  `hospitalFuncionario` int(11) NOT NULL,
  PRIMARY KEY (`codFunc`,`cargoFuncionario`,`hospitalFuncionario`) USING BTREE,
  KEY `fk_funcionario_cargo1_idx` (`cargoFuncionario`) USING BTREE,
  KEY `fk_funcionario_hospital1_idx` (`hospitalFuncionario`) USING BTREE,
  CONSTRAINT `fk_funcionario_cargo1` FOREIGN KEY (`cargoFuncionario`) REFERENCES `cargo` (`codCargo`),
  CONSTRAINT `fk_funcionario_hospital1` FOREIGN KEY (`hospitalFuncionario`) REFERENCES `hospital` (`codHospital`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Copiando dados para a tabela guilhermes_andressa_hospital.funcionario: ~2 rows (aproximadamente)
INSERT INTO `funcionario` (`codFunc`, `nomeFunc`, `cpf`, `rg`, `numRegistro`, `dataAdmissao`, `horarioTrabalho`, `cargoFuncionario`, `hospitalFuncionario`) VALUES
	(1, 'José Alencar da Fonseca Neto', '145.963.854-17', 'MG-45.321.654', '235478', '2008-05-27', '16:57:00', 1, 1),
	(2, 'Maria Garcia Lima', '203.154.121-07', 'SP-190.987.22', '12114525632', '2024-05-19', '07:00:00', 2, 1);

-- Copiando estrutura para tabela guilhermes_andressa_hospital.hospital
DROP TABLE IF EXISTS `hospital`;
CREATE TABLE IF NOT EXISTS `hospital` (
  `codHospital` int(11) NOT NULL AUTO_INCREMENT,
  `cnpjHospital` varchar(55) NOT NULL,
  `endereco` varchar(500) NOT NULL,
  `bairro` varchar(250) NOT NULL,
  `cidade` varchar(500) NOT NULL,
  `cep` varchar(50) NOT NULL,
  `uf` varchar(20) NOT NULL,
  `telefone` varchar(90) NOT NULL,
  `descricao` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`codHospital`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Copiando dados para a tabela guilhermes_andressa_hospital.hospital: ~2 rows (aproximadamente)
INSERT INTO `hospital` (`codHospital`, `cnpjHospital`, `endereco`, `bairro`, `cidade`, `cep`, `uf`, `telefone`, `descricao`) VALUES
	(1, '64.024.537/1135-80', 'Rua Coronel Azarias', 'Santa Luiza', 'Londrina', '85963-21', 'PR', '988764525', 'Especilistas no cuidade com sua saúde!'),
	(5, '123', 'Rua Pico Agudo', 'CENTRO', 'São João da Mata', '37568-000', 'MG', '359967845', 'Hospital vida');

-- Copiando estrutura para tabela guilhermes_andressa_hospital.internacao
DROP TABLE IF EXISTS `internacao`;
CREATE TABLE IF NOT EXISTS `internacao` (
  `codInternacao` int(11) NOT NULL AUTO_INCREMENT,
  `dataHora` datetime NOT NULL,
  `sala` int(11) DEFAULT NULL,
  `descricao` varchar(200) NOT NULL,
  `pacienteInternacao` int(11) NOT NULL,
  `funcionarioInternacao` int(11) NOT NULL,
  PRIMARY KEY (`codInternacao`,`pacienteInternacao`,`funcionarioInternacao`) USING BTREE,
  KEY `fk_internacao_paciente1_idx` (`pacienteInternacao`) USING BTREE,
  KEY `fk_internacao_funcionario1_idx` (`funcionarioInternacao`) USING BTREE,
  CONSTRAINT `fk_internacao_funcionario1` FOREIGN KEY (`funcionarioInternacao`) REFERENCES `funcionario` (`codFunc`),
  CONSTRAINT `fk_internacao_paciente1` FOREIGN KEY (`pacienteInternacao`) REFERENCES `paciente` (`codPaciente`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Copiando dados para a tabela guilhermes_andressa_hospital.internacao: ~0 rows (aproximadamente)
INSERT INTO `internacao` (`codInternacao`, `dataHora`, `sala`, `descricao`, `pacienteInternacao`, `funcionarioInternacao`) VALUES
	(1, '2024-11-04 19:45:09', 4, 'Internação por insuficiência respiratória!', 10, 1);

-- Copiando estrutura para tabela guilhermes_andressa_hospital.medicacao
DROP TABLE IF EXISTS `medicacao`;
CREATE TABLE IF NOT EXISTS `medicacao` (
  `codRemedio` int(11) NOT NULL AUTO_INCREMENT,
  `tipoRemedio` varchar(500) DEFAULT NULL,
  `valorRemedio` double NOT NULL,
  `nomeRemedio` varchar(300) NOT NULL,
  `fabricacao` varchar(200) DEFAULT NULL,
  `desconto` double DEFAULT NULL,
  PRIMARY KEY (`codRemedio`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Copiando dados para a tabela guilhermes_andressa_hospital.medicacao: ~6 rows (aproximadamente)
INSERT INTO `medicacao` (`codRemedio`, `tipoRemedio`, `valorRemedio`, `nomeRemedio`, `fabricacao`, `desconto`) VALUES
	(1, 'Antibiótico', 21.9, 'Amoxicilina', 'Eurofarma', 0),
	(2, 'Anti-inflamatório', 86, 'Descongex', 'Cimed', NULL),
	(3, 'Anti-inflamatório', 100, 'Allegra D', 'EuroFarma', NULL),
	(4, 'Soro', 10, 'Soro', 'Cimed', NULL),
	(7, 'Pomada', 5.88, 'Sulfato de Neomicina', 'Medley', NULL),
	(8, 'Antibiótico', 16, 'Carbenicilina', 'Cimed', NULL);

-- Copiando estrutura para tabela guilhermes_andressa_hospital.medicacao_has_internacao
DROP TABLE IF EXISTS `medicacao_has_internacao`;
CREATE TABLE IF NOT EXISTS `medicacao_has_internacao` (
  `codMedicacaoInternacao` int(11) NOT NULL AUTO_INCREMENT,
  `medicacao_codRemedio` int(11) NOT NULL,
  `internacao_codInternacao` int(11) NOT NULL,
  `quantidadeRemedio` varchar(250) NOT NULL,
  `valor` double DEFAULT NULL,
  PRIMARY KEY (`codMedicacaoInternacao`,`medicacao_codRemedio`,`internacao_codInternacao`) USING BTREE,
  KEY `fk_medicacao_has_internacao_internacao1_idx` (`internacao_codInternacao`) USING BTREE,
  KEY `fk_medicacao_has_internacao_medicacao1_idx` (`medicacao_codRemedio`) USING BTREE,
  CONSTRAINT `fk_medicacao_has_internacao_internacao1` FOREIGN KEY (`internacao_codInternacao`) REFERENCES `internacao` (`codInternacao`),
  CONSTRAINT `fk_medicacao_has_internacao_medicacao1` FOREIGN KEY (`medicacao_codRemedio`) REFERENCES `medicacao` (`codRemedio`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Copiando dados para a tabela guilhermes_andressa_hospital.medicacao_has_internacao: ~18 rows (aproximadamente)
INSERT INTO `medicacao_has_internacao` (`codMedicacaoInternacao`, `medicacao_codRemedio`, `internacao_codInternacao`, `quantidadeRemedio`, `valor`) VALUES
	(3, 4, 1, '12', 120),
	(4, 7, 1, '1', 5.88),
	(5, 1, 1, '13', 284.7),
	(6, 2, 1, '3', 258),
	(7, 1, 1, '1', 21.9),
	(8, 1, 1, '1', 21.9),
	(9, 1, 1, '1', 21.9),
	(10, 1, 1, '1', 21.9),
	(11, 1, 1, '1', 21.9),
	(12, 1, 1, '1', 21.9),
	(13, 1, 1, '1', 21.9),
	(14, 1, 1, '1', 21.9),
	(15, 1, 1, '1', 21.9),
	(16, 1, 1, '1', 21.9),
	(17, 1, 1, '1', 21.9),
	(18, 1, 1, '1', 21.9),
	(19, 1, 1, '1', 21.9),
	(20, 1, 1, '1', 21.9);

-- Copiando estrutura para tabela guilhermes_andressa_hospital.paciente
DROP TABLE IF EXISTS `paciente`;
CREATE TABLE IF NOT EXISTS `paciente` (
  `codPaciente` int(11) NOT NULL AUTO_INCREMENT,
  `nomePaciente` varchar(500) NOT NULL,
  `cpf` varchar(65) NOT NULL,
  `dataNascimento` date NOT NULL,
  `filiacao` varchar(500) DEFAULT NULL,
  `rg` varchar(45) NOT NULL,
  `endereco` varchar(500) DEFAULT NULL,
  `bairro` varchar(250) DEFAULT NULL,
  `cidade` varchar(500) DEFAULT NULL,
  `cep` varchar(50) DEFAULT NULL,
  `uf` varchar(20) DEFAULT NULL,
  `telefone` varchar(50) DEFAULT NULL,
  `planoPaciente` int(11) NOT NULL,
  PRIMARY KEY (`codPaciente`,`planoPaciente`) USING BTREE,
  KEY `fk_paciente_plano_idx` (`planoPaciente`) USING BTREE,
  CONSTRAINT `fk_paciente_plano` FOREIGN KEY (`planoPaciente`) REFERENCES `plano` (`codPlano`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Copiando dados para a tabela guilhermes_andressa_hospital.paciente: ~4 rows (aproximadamente)
INSERT INTO `paciente` (`codPaciente`, `nomePaciente`, `cpf`, `dataNascimento`, `filiacao`, `rg`, `endereco`, `bairro`, `cidade`, `cep`, `uf`, `telefone`, `planoPaciente`) VALUES
	(10, 'Welington Morais da Silva', '12087654321', '2025-06-11', 'Lúcio Morais e Janaina da Silva Gonçalves', '1234', 'Rua das Abóboras ', 'Centro', 'Machado', '37750000', 'MG', '1234123', 5),
	(12, 'Amilton', '12087654321', '2025-06-11', 'Lúcio Morais e Janaina da Silva Gonçalves', '1234', 'Rua das Abóboras ', 'Centro', 'Machado', '37750000', 'MG', '1234123', 2),
	(13, 'Joaquim', '15455215', '2025-07-03', 'Lúcio Morais e Janaina da Silva Gonçalves', 'MG-12356', 'Rua Louvre', 'Centro', 'Machado', '3558620', 'tm', '3223456789', 5),
	(14, 'Kiara', '12344565', '2025-08-06', 'Lúcio Morais e Janaina da Silva Gonçalves', 'MG-12356', 'Rua Louvre', 'Centro', 'Machado', '3558620', 'tm', '3223456789', 1);

-- Copiando estrutura para tabela guilhermes_andressa_hospital.password_reset_token
DROP TABLE IF EXISTS `password_reset_token`;
CREATE TABLE IF NOT EXISTS `password_reset_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `token_hash` varchar(64) NOT NULL,
  `created_at` datetime NOT NULL,
  `expires_at` datetime NOT NULL,
  `used` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `token_hash` (`token_hash`),
  CONSTRAINT `password_reset_token_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Copiando dados para a tabela guilhermes_andressa_hospital.password_reset_token: ~16 rows (aproximadamente)
INSERT INTO `password_reset_token` (`id`, `user_id`, `token_hash`, `created_at`, `expires_at`, `used`) VALUES
	(1, 1, 'd807fc1e053781055573bc872f47f136c19f1b463d4b50511a7f2354ff7644f2', '2025-08-12 14:43:32', '2025-08-12 15:43:32', 0),
	(2, 1, 'eb592fec070d8c792e44db4acbccc94effb546b3e7cc1aba8b51bef7e6527b3d', '2025-08-12 14:48:15', '2025-08-12 15:48:15', 0),
	(3, 1, 'c8af7c975aefee8c6c2dedf104c756b9aefaa092a71cd5ec5b76e415032be055', '2025-08-12 14:51:04', '2025-08-12 15:51:04', 0),
	(4, 1, 'f5fea09d793a70706a53d0f42b2256cf8e13ed948bdd85efd451423ec16073c1', '2025-08-12 14:55:54', '2025-08-12 15:55:54', 1),
	(5, 5, '7f3eb20bf0f0713ab4d837275f76f479693b44dbfdccf25d0ea4153b105ed527', '2025-08-12 14:57:59', '2025-08-12 15:57:59', 1),
	(6, 6, '9880ec167a51cb09f48775fbbdc3ed3d8ba39df59e76039093d90680533c1fb0', '2025-08-12 15:00:25', '2025-08-12 16:00:25', 1),
	(7, 7, 'a29c4d2e940fd51f17320e930d75485d6e67afb8b932f71e83dc523bfa289b73', '2025-08-29 13:36:16', '2025-08-29 14:36:16', 0),
	(8, 7, 'b8ec39ef986ba86af077a8a10ec66b67f9a69c9612d2163559f1f0b4b3b74b59', '2025-08-29 13:41:25', '2025-08-29 14:41:25', 0),
	(9, 7, '2965492d6a4fbc844b27813ac7dbc6e20be38a47506100a531389a1bd65abeae', '2025-08-29 13:42:00', '2025-08-29 14:42:00', 0),
	(10, 7, '710b056b1d1ed8b5eefc9d1570c3f14133f34cbcb954fa5c2b4d5047e40f9ef3', '2025-08-29 13:45:41', '2025-08-29 14:45:41', 1),
	(11, 7, '2bec45688b0e725bfdbd05ee327362358d4dda4ceaa4be4950f0c8e660add78e', '2025-08-29 14:30:00', '2025-08-29 15:30:00', 0),
	(12, 8, '339e898985e03da8ea245817e1775e58a4f09480a6f510ed545428b03aac933f', '2025-08-29 18:00:37', '2025-08-29 19:00:37', 1),
	(13, 1, 'bfe8d664e02b1c3c9747ca8e97d0394e9b633eb6f8369ac67ccea3b81432e8e4', '2025-08-29 18:03:40', '2025-08-29 19:03:40', 0),
	(14, 8, 'c8e79162f4bd89171356c48280231598255a5310223bd76c5e7bc7ca1775c27c', '2025-08-29 18:05:20', '2025-08-29 19:05:20', 0),
	(15, 1, '38b59e2ed47b23a0df7cc02aa91197c08c1db2e2a5504f6900cee7b2979aecea', '2025-08-29 18:46:20', '2025-08-29 19:46:20', 0),
	(16, 1, '0f3b8ffdeb86bdd643adb5d08186c3678e721a46c547d32066538fc05e1c6c16', '2025-08-29 19:09:02', '2025-08-29 20:09:02', 0),
	(17, 8, 'fcb272ab6440c7fca11fc76a1f7117665130b166251c70e9070dd913a9ca7c95', '2025-08-30 10:49:41', '2025-08-30 11:49:41', 0);

-- Copiando estrutura para tabela guilhermes_andressa_hospital.plano
DROP TABLE IF EXISTS `plano`;
CREATE TABLE IF NOT EXISTS `plano` (
  `codPlano` int(11) NOT NULL AUTO_INCREMENT,
  `nomePlano` varchar(300) NOT NULL,
  `valorPlano` double DEFAULT NULL,
  `tipoPlano` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`codPlano`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Copiando dados para a tabela guilhermes_andressa_hospital.plano: ~4 rows (aproximadamente)
INSERT INTO `plano` (`codPlano`, `nomePlano`, `valorPlano`, `tipoPlano`) VALUES
	(1, 'Unimed', 220, 'Privado'),
	(2, 'Ipsemg', 189.9, 'Para servidores públicos'),
	(5, 'Bradesco saúde', 360, 'Privado'),
	(6, 'Amil', 90, 'Empresarial');

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_atualizaAmbulatorio
DROP PROCEDURE IF EXISTS `p_atualizaAmbulatorio`;
DELIMITER //
CREATE PROCEDURE `p_atualizaAmbulatorio`(
	IN `codAmbulatorioAltera` INT,
	IN `horarioAltera` TIME,
	IN `quantAmbulatorioAltera` INT,
	IN `hospitalAltera` INT
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM ambulatorio WHERE codAmbulatorio = codAmbulatorioAltera;
	if(@contador = 1)
	then UPDATE ambulatorio SET horarioAtendimento = horarioAltera, quantAmbulancias =  quantAmbulatorioAltera, hospital_codHospital = hospitalAltera 
	WHERE codAmbulatorio = codAmbulatorioAltera;
	SELECT * FROM ambulatorio;
	SELECT * FROM ambulatorio WHERE codAmbulatorio = codAmbulatorioAltera;
	ELSE SELECT "Código inexistente!" AS erro;
	END if;
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_atualizaCargo
DROP PROCEDURE IF EXISTS `p_atualizaCargo`;
DELIMITER //
CREATE PROCEDURE `p_atualizaCargo`(
	IN `codCargoAltera` INT,
	IN `nomeCargoAltera` VARCHAR(250),
	IN `salarioInicialAltera` DOUBLE,
	IN `descricaoAltera` VARCHAR(250)
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM cargo WHERE codCargo = codCargoAltera;
	if(@contador = 1)
	then UPDATE cargo SET nomeCargo = nomeCargoAltera, salarioInicial =  salarioInicialAltera, descricao = descricaoAltera 
	WHERE codCargo = codCargoAltera;
	SELECT * FROM cargo;
	SELECT * FROM cargo WHERE codCargo = codCargoAltera;
	ELSE SELECT "Código inexistente!" AS erro;
	END if;
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_atualizaConsulta
DROP PROCEDURE IF EXISTS `p_atualizaConsulta`;
DELIMITER //
CREATE PROCEDURE `p_atualizaConsulta`(
	IN `codAtualiza` INT,
	IN `diagnosticoAtualiza` VARCHAR(400),
	IN `dataConsultaAtualiza` DATE,
	IN `pacienteAtualiza` INT,
	IN `funcionarioAtualiza` INT
)
BEGIN
SELECT COUNT(*) INTO @contador FROM consulta WHERE codConsulta = codAtualiza;
	if(@contador = 1)
	then UPDATE consulta SET diagnostico = diagnosticoAtualiza, dataConsulta =  dataConsultaAtualiza, paciente_codPaciente = pacienteAtualiza, funcionario_codFunc = funcionarioAtualiza WHERE codConsulta = codAtualiza;
	SELECT * FROM consulta;
	SELECT * FROM consulta WHERE codConsulta = codAtualiza;
	ELSE SELECT "Código inexistente!" AS erro;
	END if;

END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_atualizaExame
DROP PROCEDURE IF EXISTS `p_atualizaExame`;
DELIMITER //
CREATE PROCEDURE `p_atualizaExame`(
	IN `codExameAltera` INT,
	IN `tipoExameAltera` VARCHAR(300),
	IN `valorAltera` DOUBLE,
	IN `resultadoAltera` VARCHAR(300),
	IN `codPacienteAltera` INT,
	IN `codFuncAltera` INT
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM exame WHERE codExame = codExameAltera;
	if(@contador = 1)
	then UPDATE exame SET tipoExame = tipoExameAltera, valor =  valorAltera, resultado = resultadoAltera 
	WHERE codExame = codExameAltera;
	SELECT * FROM exame;
	SELECT * FROM exame WHERE codExame = codExameAltera;
	ELSE SELECT "Código inexistente!" AS erro;
	END if;
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_atualizaFuncionario
DROP PROCEDURE IF EXISTS `p_atualizaFuncionario`;
DELIMITER //
CREATE PROCEDURE `p_atualizaFuncionario`(
	IN `codFuncAltera` INT,
	IN `nomeFuncAltera` VARCHAR(500),
	IN `cpfAltera` VARCHAR(65),
	IN `rgAltera` VARCHAR(45),
	IN `numRegistroAltera` VARCHAR(200),
	IN `dataAdmissaoAltera` DATE,
	IN `horarioTrabalhoAltera` TIME,
	IN `codCargoAltera` INT,
	IN `codHospitalAltera` INT
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM funcionario WHERE codFunc = codFuncAltera;
	if(@contador = 1)
	then UPDATE funcionario SET nomeFunc = nomeFuncAltera, cpf =  cpfAltera, rg = rgAltera, numRegistro = numRegistroAltera, 
	dataAdmissao = dataAdmissaoAltera, horarioTrabalho = horarioTrabalhoAltera, cargo_codCargo = codCargoAltera, hospital_codHospital = codHospitalAltera
	WHERE codFunc = codFuncAltera;
	SELECT codFunc,nomeFunc,cpf,rg, numRegistro, date_format(dataAdmissao,"%d/%m/%Y") AS dataAdmissao, TIME_FORMAT(horarioTrabalho, "%h:%i") AS horarioTrabalho, cargo_codCargo, hospital_codHospital FROM funcionario;
	SELECT codFunc,nomeFunc,cpf,rg, numRegistro, date_format(dataAdmissao,"%d/%m/%Y") AS dataAdmissao, TIME_FORMAT(horarioTrabalho, "%h:%i") AS horarioTrabalho, cargo_codCargo, hospital_codHospital FROM funcionario WHERE codFunc = codFuncAltera;
	ELSE SELECT "Código inexistente!" AS erro;
	END if;
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_atualizaHospital
DROP PROCEDURE IF EXISTS `p_atualizaHospital`;
DELIMITER //
CREATE PROCEDURE `p_atualizaHospital`(
	IN `codHospitalAltera` INT,
	IN `cnpjHospitalAltera` VARCHAR(55),
	IN `enderecoAltera` VARCHAR(500),
	IN `bairroAltera` VARCHAR(250),
	IN `cidadeAltera` VARCHAR(500),
	IN `cepAltera` VARCHAR(50),
	IN `ufAltera` VARCHAR(20),
	IN `telefoneAltera` VARCHAR(90),
	IN `descricaoAltera` VARCHAR(500)
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM hospital WHERE codHospital = codHospitalAltera;
	if(@contador = 1)
	then UPDATE hospital SET cnpjHospital = cnpjHospitalAltera, endereco =  enderecoAltera, bairro = bairroAltera, cidade = cidadeAltera,
	cep = cepAltera, uf = ufAltera, telefone = telefoneAltera, descricao = descricaoAltera WHERE codHospital = codHospitalAltera;
	SELECT * FROM hospital;
	SELECT * FROM hospital WHERE codHospital = codHospitalAltera;
	ELSE SELECT "Código inexistente!" AS erro;
	END if;
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_atualizaInternacao
DROP PROCEDURE IF EXISTS `p_atualizaInternacao`;
DELIMITER //
CREATE PROCEDURE `p_atualizaInternacao`(
	IN `codInternacaoAltera` INT,
	IN `dataHoraAltera` DATETIME,
	IN `salaAltera` INT,
	IN `descricaoAltera` VARCHAR(200),
	IN `codPacienteAltera` INT,
	IN `codFuncAltera` INT
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM internacao WHERE codInternacao = codInternacaoAltera;
	if(@contador = 1)
	then UPDATE internacao SET dataHora = dataHoraAltera, sala =  salaAltera, descricao = descricaoAltera, paciente_codPaciente = codPacienteAltera,
	funcionario_codFunc = codFuncAltera WHERE codInternacao = codInternacaoAltera;
	SELECT * FROM internacao;
	SELECT * FROM internacao WHERE codInternacao = codInternacaoAltera;
	ELSE SELECT "Código inexistente!" AS erro;
	END if;
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_atualizaMedicacao
DROP PROCEDURE IF EXISTS `p_atualizaMedicacao`;
DELIMITER //
CREATE PROCEDURE `p_atualizaMedicacao`(
	IN `codRemedioAtualiza` INT,
	IN `tipoRemedioAtualiza` VARCHAR(500),
	IN `valorRemedioAtualiza` DOUBLE,
	IN `nomeRemedioAtualiza` VARCHAR(300),
	IN `fabricacaoAtualiza` VARCHAR(200)
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM medicacao WHERE codRemedio = codRemedioAltualiza;
	if(@contador = 1)
	then UPDATE medicacao SET tipoRemedio = tipoRemedioAtualiza, valorRemedio = valorRemedioAtualiza, 
	nomeRemedio = nomeRemedioAtualiza, fabricacao = fabricacaoAtualiza WHERE codRemedio = codRemedioAltualiza;
	SELECT * FROM medicacao;
	SELECT * FROM medicacao WHERE codRemedio = codRemedioAltualiza;
	ELSE SELECT "Código inexistente!" AS erro;
	END if;
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_atualizaMedicacaoAmbulatorio
DROP PROCEDURE IF EXISTS `p_atualizaMedicacaoAmbulatorio`;
DELIMITER //
CREATE PROCEDURE `p_atualizaMedicacaoAmbulatorio`(
	IN `codMedicacaoAmbulatorioAltera` INT,
	IN `codAmbulatorioAltera` INT,
	IN `codMedicacaoAltera` INT,
	IN `quantidadeAltera` VARCHAR(250)
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM ambulatorio_has_medicacao WHERE codMedicacaoAmbulatorio = codMedicacaoAmbulatorioAltera;
	if(@contador = 1)
	then UPDATE ambulatorio_has_medicacao SET ambulatorio_codAmbulatorio = codAmbulatorioAltera, 
	medicacao_codMedicacao =  codMedicacaoAltera, quantidadeRemedio = quantidadeAltera WHERE codHospital = codHospitalAltera;
	SELECT * FROM ambulatorio_has_medicacao;
	SELECT * FROM ambulatorio_has_medicacao WHERE codMedicacaoAmbulatorio = codMedicacaoAmbulatorioAltera;
	ELSE SELECT "Código inexistente!" AS erro;
	END if;
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_atualizaMedicacaoInternacao
DROP PROCEDURE IF EXISTS `p_atualizaMedicacaoInternacao`;
DELIMITER //
CREATE PROCEDURE `p_atualizaMedicacaoInternacao`(
	IN `codMedicacaoInternacaoAltera` INT,
	IN `codRemedioAltera` INT,
	IN `codInternacaoAltera` INT,
	IN `quantidadeAltera` INT
)
BEGIN
SELECT COUNT(*) INTO @contador FROM medicacao_has_internacao WHERE codMedicacaoInternacao = codMedicacaoInternacaoAltera;
	if(@contador = 1)
	then UPDATE medicacao_has_internacao SET medicacao_codRemedio = codRemedioAltera, 
	internacao_codInternacao =  codInternacaoAltera, quantidadeRemedio = quantidadeAltera WHERE codMedicacaoInternacao = codMedicacaoInternacaoAltera;
	SELECT * FROM medicacao_has_internacao;
	SELECT * FROM medicacao_has_internacao WHERE codMedicacaoInternacao = codMedicacaoInternacaoAltera;
	ELSE SELECT "Código inexistente!" AS erro;
	END if;
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_atualizaPaciente
DROP PROCEDURE IF EXISTS `p_atualizaPaciente`;
DELIMITER //
CREATE PROCEDURE `p_atualizaPaciente`(
	IN `codAtualiza` INT,
	IN `nomeAltera` VARCHAR(500),
	IN `cpfAltera` VARCHAR(65),
	IN `dataAltera` DATE,
	IN `filiacaoAltera` VARCHAR(500),
	IN `rgAltera` VARCHAR(45),
	IN `enderecoAltera` VARCHAR(500),
	IN `bairroAltera` VARCHAR(250),
	IN `cidadeAltera` VARCHAR(500),
	IN `cepAltera` VARCHAR(50),
	IN `ufAltera` VARCHAR(20),
	IN `telefoneAltera` VARCHAR(50),
	IN `planocodAltera` INT
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM paciente WHERE codPaciente = codAtualiza;
	if(@contador = 1)
	then UPDATE paciente SET nomePaciente = nomeAltera, cpf =  cpfAltera,dataNascimento = dataAltera,
	 filiacao = filiacaoAltera, rg = rgAltera, endereco = enderecoAltera, bairro = bairroAltera, cidade = cidadeAltera, 
	cep = cepAltera, uf = ufAltera, telefone = telefoneAltera, plano_codPlano = planocodAltera WHERE codPaciente = codAtualiza;
	SELECT codPaciente,nomePaciente,cpf,date_format(dataNascimento,"%d/%m/%Y") AS dataNascimento, filiacao, rg,endereco,bairro,cidade,cep,uf,telefone,plano_codPlano FROM paciente;
 	SELECT codPaciente,nomePaciente,cpf,date_format(dataNascimento,"%d/%m/%Y") AS dataNascimento, filiacao, rg,endereco,bairro,cidade,cep,uf,telefone,plano_codPlano FROM paciente WHERE codPaciente = codAtualiza;
	ELSE SELECT "Código inexistente!" AS erro;
	END if;
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_atualizaPlano
DROP PROCEDURE IF EXISTS `p_atualizaPlano`;
DELIMITER //
CREATE PROCEDURE `p_atualizaPlano`(
	IN `codAtualiza` INT,
	IN `nomeAtualiza` VARCHAR(300),
	IN `valorAtualiza` DOUBLE,
	IN `tipoAtualiza` VARCHAR(300)
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM plano WHERE codPlano = codAtualiza;
	if(@contador = 1)
	then UPDATE plano SET nomePlano = nomeAtualiza, valorPlano =  valorAtualiza, tipoPlano = tipoAtualiza WHERE codPlano = codAtualiza;
	SELECT * FROM plano;
	SELECT * FROM plano WHERE codPlano = codAtualiza;
	ELSE SELECT "Código inexistente!" AS erro;
	END if;
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_deletaAmbulatorio
DROP PROCEDURE IF EXISTS `p_deletaAmbulatorio`;
DELIMITER //
CREATE PROCEDURE `p_deletaAmbulatorio`(
	IN `codExcluir` INT
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM ambulatorio WHERE codAmbulatorio = codExcluir;
	if(@contador = 1)
	 then DELETE FROM ambulatorio WHERE codAmbulatorio = codExcluir;
	SELECT codAmbulatorio, TIME_FORMAT(horarioAtendimento, "%h:%i") AS horarioAtendimento, quantAmbulancias, hospital_codHospital FROM ambulatorio;
	ELSE SELECT "Código de exclusão inexistente!" AS erro;
	END if;	
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_deletaAmbulatorioMedicacao
DROP PROCEDURE IF EXISTS `p_deletaAmbulatorioMedicacao`;
DELIMITER //
CREATE PROCEDURE `p_deletaAmbulatorioMedicacao`(
	IN `codExcluir` INT
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM ambulatorio_has_medicacao WHERE codMedicacaoAmbulatorio = codExcluir;
	if(@contador = 1)
	 then DELETE FROM ambulatorio_has_medicacao WHERE codMedicacaoAmbulatorio = codExcluir;
	SELECT * FROM ambulatorio_has_medicacao;
	ELSE SELECT "Código de exclusão inexistente!" AS erro;
	END if;
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_deletaCargo
DROP PROCEDURE IF EXISTS `p_deletaCargo`;
DELIMITER //
CREATE PROCEDURE `p_deletaCargo`(
	IN `codExcluir` INT
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM cargo WHERE codCargo = codExcluir;
	if(@contador = 1)
	 then DELETE FROM cargo WHERE codCargo = codExcluir;
	SELECT * FROM cargo;
	ELSE SELECT "Código de exclusão inexistente!" AS erro;
	END if;	
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_deletaConsulta
DROP PROCEDURE IF EXISTS `p_deletaConsulta`;
DELIMITER //
CREATE PROCEDURE `p_deletaConsulta`(
	IN `codExcluir` INT
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM consulta WHERE codConsulta = codExcluir;
	if(@contador = 1)
	 then DELETE FROM consulta WHERE codConsulta = codExcluir;
	SELECT codConsulta, diagnostico, date_format(dataConsulta,"%d/%m/%Y") AS dataConsulta, paciente_codPaciente, funcionario_codFunc FROM consulta;
	ELSE SELECT "Código de exclusão inexistente!" AS erro;
	END if;	
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_deletaExame
DROP PROCEDURE IF EXISTS `p_deletaExame`;
DELIMITER //
CREATE PROCEDURE `p_deletaExame`(
	IN `codExcluir` INT
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM exame WHERE codExame = codExcluir;
	if(@contador = 1)
		then DELETE FROM exame WHERE codExame = codExcluir;
	SELECT * FROM exame;
	ELSE SELECT "Código de exclusão inexistente!" AS erro;
	END if;
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_deletaFuncionario
DROP PROCEDURE IF EXISTS `p_deletaFuncionario`;
DELIMITER //
CREATE PROCEDURE `p_deletaFuncionario`(
	IN `codExcluir` INT
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM funcionario WHERE codFunc = codExcluir;
	if(@contador = 1)
	 then DELETE FROM funcionario WHERE codFunc = codExcluir;
	SELECT codFunc,nomeFunc,cpf,rg, numRegistro, date_format(dataAdmissao,"%d/%m/%Y") AS dataAdmissao, TIME_FORMAT(horarioTrabalho, "%h:%i") AS horarioTrabalho, cargo_codCargo, hospital_codHospital FROM funcionario;
	ELSE SELECT "Código de exclusão inexistente!" AS erro;
	END if;	
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_deletaHospital
DROP PROCEDURE IF EXISTS `p_deletaHospital`;
DELIMITER //
CREATE PROCEDURE `p_deletaHospital`(
	IN `codExcluir` INT
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM hospital WHERE codHospital = codExcluir;
	if(@contador = 1)
	 then DELETE FROM hospital WHERE codHospital = codExcluir;
	SELECT * FROM hospital;
	ELSE SELECT "Código de exclusão inexistente!" AS erro;
	END if;	
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_deletaInternacao
DROP PROCEDURE IF EXISTS `p_deletaInternacao`;
DELIMITER //
CREATE PROCEDURE `p_deletaInternacao`(
	IN `codExcluir` INT
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM internacao WHERE codInternacao = codExcluir;
	if(@contador = 1)
	 then DELETE FROM internacao WHERE codInternacao = codExcluir;
	SELECT * FROM internacao;
	ELSE SELECT "Código de exclusão inexistente!" AS erro;
	END if;	
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_deletaMedicacao
DROP PROCEDURE IF EXISTS `p_deletaMedicacao`;
DELIMITER //
CREATE PROCEDURE `p_deletaMedicacao`(
	IN `codExcluir` INT
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM medicacao WHERE codRemedio = codExcluir;
	if(@contador = 1)
	 then DELETE FROM medicacao WHERE codRemedio = codExcluir;
	SELECT * FROM medicacao;
	ELSE SELECT "Código de exclusão inexistente!" AS erro;
	END if;		
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_deletaMedInternacao
DROP PROCEDURE IF EXISTS `p_deletaMedInternacao`;
DELIMITER //
CREATE PROCEDURE `p_deletaMedInternacao`(
	IN `codExcluir` INT
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM medicacao_has_internacao WHERE codMedicacaoInternacao = codExcluir;
	if(@contador = 1)
	 then DELETE FROM medicacao_has_internacao WHERE codMedicacaoInternacao = codExcluir;
	SELECT * FROM medicacao_has_internacao;
	ELSE SELECT "Código de exclusão inexistente!" AS erro;
	END if;
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_deletaPaciente
DROP PROCEDURE IF EXISTS `p_deletaPaciente`;
DELIMITER //
CREATE PROCEDURE `p_deletaPaciente`(
	IN `codExcluir` INT
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM paciente WHERE codPaciente = codExcluir;
	if(@contador = 1)
	 then DELETE FROM paciente WHERE codPaciente = codExcluir;
	SELECT codPaciente,nomePaciente,cpf,date_format(dataNascimento,"%d/%m/%Y") AS dataNascimento, filiacao, rg,endereco,bairro,cidade,cep,uf,telefone,plano_codPlano FROM paciente; 
	ELSE SELECT "Código de exclusão inexistente!" AS erro;
	END if;	
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_deletaPlano
DROP PROCEDURE IF EXISTS `p_deletaPlano`;
DELIMITER //
CREATE PROCEDURE `p_deletaPlano`(
	IN `codExcluir` INT
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM plano WHERE codPlano = codExcluir;
	if(@contador = 1)
	 then DELETE FROM plano WHERE codPlano = codExcluir; 
	SELECT * FROM plano; 
	ELSE SELECT "Código de exclusão inexistente!" AS erro;
	END if;
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_insereAmbulatorio
DROP PROCEDURE IF EXISTS `p_insereAmbulatorio`;
DELIMITER //
CREATE PROCEDURE `p_insereAmbulatorio`(
	IN `horarioInsere` TIME,
	IN `quantidadeInsere` INT,
	IN `hospitalInsere` INT
)
BEGIN
	INSERT INTO ambulatorio VALUES(NULL, horarioInsere, quantidadeInsere, hospitalInsere);
	SELECT * FROM ambulatorio;
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_insereCargo
DROP PROCEDURE IF EXISTS `p_insereCargo`;
DELIMITER //
CREATE PROCEDURE `p_insereCargo`(
	IN `nomeInsere` VARCHAR(250),
	IN `salarioInsere` DOUBLE,
	IN `descricaoInsere` VARCHAR(250)
)
BEGIN
	INSERT INTO cargo VALUES(NULL,nomeInsere,salarioInsere,descricaoInsere);
	SELECT * FROM cargo; 
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_insereConsulta
DROP PROCEDURE IF EXISTS `p_insereConsulta`;
DELIMITER //
CREATE PROCEDURE `p_insereConsulta`(
	IN `diagnosticoInsere` VARCHAR(400),
	IN `dataInsere` DATE,
	IN `pacienteInsere` INT,
	IN `funcionarioInsere` INT
)
BEGIN
	INSERT INTO consulta VALUES(NULL, diagnosticoInsere, dataInsere, pacienteInsere, funcionarioInsere);
	SELECT codConsulta, diagnostico,  date_format(dataConsulta,"%d/%m/%Y") AS dataConsulta, paciente_codPaciente, funcionario_codFuncionario FROM consulta;
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_insereExame
DROP PROCEDURE IF EXISTS `p_insereExame`;
DELIMITER //
CREATE PROCEDURE `p_insereExame`(
	IN `tipoInsere` VARCHAR(300),
	IN `valorInsere` DOUBLE,
	IN `resultadoInsere` VARCHAR(300),
	IN `pacienteInsere` INT,
	IN `funcionarioInsere` INT
)
BEGIN
	INSERT INTO exame VALUES(NULL, tipoInsere, valorInsere, resultadoInsere, pacienteInsere, funcionarioInsere);
	SELECT * FROM exame;
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_insereFuncionario
DROP PROCEDURE IF EXISTS `p_insereFuncionario`;
DELIMITER //
CREATE PROCEDURE `p_insereFuncionario`(
	IN `nomeInsere` VARCHAR(500),
	IN `cpfInsere` VARCHAR(65),
	IN `rgInsere` VARCHAR(45),
	IN `registroInsere` VARCHAR(200),
	IN `dataInsere` DATE,
	IN `horarioInsere` TIME,
	IN `cargoInsere` INT,
	IN `hospitalInsere` INT
)
BEGIN
	INSERT INTO funcionario VALUES(NULL,nomeInsere,cpfInsere,rgInsere,registroInsere,dataInsere,horarioInsere,cargoInsere,hospitalInsere);
	SELECT codFunc,nomeFunc,cpf,rg, numRegistro, date_format(dataAdmissao,"%d/%m/%Y") AS dataAdmissao, TIME_FORMAT(horarioTrabalho, "%h:%i") AS horarioTrabalho, cargo_codCargo, hospital_codHospital FROM funcionario;
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_insereHospital
DROP PROCEDURE IF EXISTS `p_insereHospital`;
DELIMITER //
CREATE PROCEDURE `p_insereHospital`(
	IN `cnpjInsere` VARCHAR(55),
	IN `enderecoInsere` VARCHAR(500),
	IN `bairroInsere` VARCHAR(250),
	IN `cidadeInsere` VARCHAR(500),
	IN `cepInsere` VARCHAR(50),
	IN `ufInsere` VARCHAR(20),
	IN `telefoneInsere` VARCHAR(90),
	IN `descricaoInsere` VARCHAR(500)
)
BEGIN
	INSERT INTO hospital VALUES(NULL,cnpjInsere,enderecoInsere,bairroInsere,cidadeInsere,cepInsere,ufInsere,telefoneInsere,descricaoInsere);
	SELECT * FROM hospital;
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_insereInternacao
DROP PROCEDURE IF EXISTS `p_insereInternacao`;
DELIMITER //
CREATE PROCEDURE `p_insereInternacao`(
	IN `dataHoraInsere` DATETIME,
	IN `salaInsere` INT,
	IN `descricaoInsere` VARCHAR(200),
	IN `pacienteInsere` INT,
	IN `funcionarioInsere` INT
)
BEGIN
	INSERT INTO internacao VALUES(NULL, dataHoraInsere, salaInsere, descricaoInsere, pacienteInsere, funcionarioInsere);
	SELECT * FROM internacao;
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_insereMedicacao
DROP PROCEDURE IF EXISTS `p_insereMedicacao`;
DELIMITER //
CREATE PROCEDURE `p_insereMedicacao`(
	IN `tipoInsere` VARCHAR(500),
	IN `valorInsere` DOUBLE,
	IN `nomeInsere` VARCHAR(300),
	IN `fabricacaoInsere` VARCHAR(200)
)
BEGIN
	INSERT INTO medicacao VALUES(NULL, tipoInsere, valorInsere, nomeInsere, fabricacaoInsere);
	SELECT * FROM medicacao;
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_inserePaciente
DROP PROCEDURE IF EXISTS `p_inserePaciente`;
DELIMITER //
CREATE PROCEDURE `p_inserePaciente`(
	IN `nomeInsere` VARCHAR(500),
	IN `cpfInsere` VARCHAR(65),
	IN `dataNascimentoInsere` DATE,
	IN `filiacaoInsere` VARCHAR(500),
	IN `rgInsere` VARCHAR(45),
	IN `enderecoInsere` VARCHAR(500),
	IN `bairroInsere` VARCHAR(250),
	IN `cidadeInsere` VARCHAR(500),
	IN `cepInsere` VARCHAR(50),
	IN `ufInsere` VARCHAR(20),
	IN `telefoneInsere` VARCHAR(50),
	IN `planoInsere` INT
)
BEGIN
	INSERT INTO paciente VALUES(NULL,nomeInsere,cpfInsere,dataNascimentoInsere, filiacaoInsere,rgInsere,enderecoInsere,bairroInsere,cidadeInsere,cepInsere,ufInsere,telefoneInsere,planoInsere);
	SELECT codPaciente,nomePaciente,cpf,date_format(dataNascimento,"%d/%m/%Y") AS dataNascimento, filiacao, rg,endereco,bairro,cidade,cep,uf,telefone,plano_codPlano FROM paciente; 
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_inserePlano
DROP PROCEDURE IF EXISTS `p_inserePlano`;
DELIMITER //
CREATE PROCEDURE `p_inserePlano`(
	IN `nomeInsere` VARCHAR(300),
	IN `valorInsere` DOUBLE,
	IN `tipoInsere` VARCHAR(300)
)
BEGIN
INSERT INTO plano VALUES(NULL,nomeInsere,valorInsere,tipoInsere);
SELECT * FROM plano; 
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_medicacaoAmbulatorioInsere
DROP PROCEDURE IF EXISTS `p_medicacaoAmbulatorioInsere`;
DELIMITER //
CREATE PROCEDURE `p_medicacaoAmbulatorioInsere`(
	IN `ambulatorioInsere` INT,
	IN `medicacaoInsere` INT,
	IN `quantidadeRemedioInsere` VARCHAR(250)
)
BEGIN
	INSERT INTO ambulatorio_has_medicacao VALUES(NULL, ambulatorioInsere, medicacaoInsere, quantidadeRemedioInsere);
	SELECT * FROM ambulatorio_has_medicacao;
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_medicacaoInternacaoInsere
DROP PROCEDURE IF EXISTS `p_medicacaoInternacaoInsere`;
DELIMITER //
CREATE PROCEDURE `p_medicacaoInternacaoInsere`(
	IN `medicacaoInsere` INT,
	IN `internacaoInsere` INT,
	IN `quantidadeRemedioInsere` VARCHAR(250)
)
BEGIN
	INSERT INTO medicacao_has_internacao VALUES(NULL, medicacaoInsere, internacaoInsere, quantidadeRemedioInsere);
	SELECT * FROM medicacao_has_internacao;
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_relatorioFuncionario
DROP PROCEDURE IF EXISTS `p_relatorioFuncionario`;
DELIMITER //
CREATE PROCEDURE `p_relatorioFuncionario`(
	IN `tipoRelatorio` INT
)
BEGIN
	if(tipoRelatorio = 1)
	# tipo de relatorio 1 contará a quantidade de funcionarios registrados na tabela
	then SELECT COUNT(*) AS quantidadeFuncionario FROM funcionario;
		ELSE if(tipoRelatorio = 2)
		#tipo de relatorio 2 mostrará informações dos funcionários com informações correspondentes aos seus respectivos cargos
		then SELECT f.nomeFunc, f.numRegistro, c.nomeCargo, c.salarioInicial FROM funcionario AS f INNER JOIN cargo AS c ON c.codCargo = f.cargo_codCargo;
			ELSE if (tipoRelatorio = 3)
			# tipo de relatorio 3 mostrará informações dos funcionários com informações correspondentes das internações por eles realizadas.
			then SELECT f.nomeFunc, f.cpf, f.numRegistro, f.rg, i.dataHora, i.sala, i.descricao, p.nomePaciente, p.cpf,
			pl.nomePlano FROM funcionario AS f INNER JOIN internacao AS i INNER JOIN paciente AS p INNER JOIN plano AS pl 
			ON f.codFunc = i.funcionario_codFunc AND p.codPaciente = i.paciente_codPaciente AND pl.codPlano = p.plano_codPlano;
				ELSE SELECT "Tipo de relatório inexistente" AS erro; 
			END if;
		END if;
	END if; 
END//
DELIMITER ;

-- Copiando estrutura para procedure guilhermes_andressa_hospital.p_relatorioPaciente
DROP PROCEDURE IF EXISTS `p_relatorioPaciente`;
DELIMITER //
CREATE PROCEDURE `p_relatorioPaciente`(
	IN `tipoRelatorio` INT
)
BEGIN
#tipo 1 - tira o relatório dos dados de preenchimento obrigatório dos pacientes
	if(tipoRelatorio=1)
	then SELECT nomePaciente, cpf, dataNascimento, rg, plano_codPlano FROM paciente;
#tipo 2 - tira o relatório de todos os dados dos pacientes, listando seus nomes em ordem alfabética
		ELSE if (tipoRelatorio=2)
		then SELECT * FROM paciente ORDER BY nomePaciente ASC;
#tipo 3 - tira o relatório da internação de um determminado paciente, com seus dados.
			ELSE if (tipoRelatorio=3)
			then SELECT p.nomePaciente,p.cpf,p.rg,i.dataHora,i.sala,i.descricao FROM paciente AS p INNER JOIN internacao AS i ON p.codPaciente=i.paciente_codPaciente;
				ELSE SELECT "Tipo de relatório inexistente!" AS erro;
			END if;
		END if;
	END if;
END//
DELIMITER ;

-- Copiando estrutura para tabela guilhermes_andressa_hospital.smtp_config
DROP TABLE IF EXISTS `smtp_config`;
CREATE TABLE IF NOT EXISTS `smtp_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `provider` varchar(50) NOT NULL,
  `from_email` varchar(255) NOT NULL,
  `from_name` varchar(100) NOT NULL,
  `host` varchar(255) NOT NULL,
  `port` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password_app` varchar(512) NOT NULL,
  `auth` tinyint(1) NOT NULL DEFAULT 1,
  `starttls` tinyint(1) NOT NULL DEFAULT 1,
  `ssl` tinyint(1) NOT NULL DEFAULT 0,
  `active` tinyint(1) NOT NULL DEFAULT 0,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `idx_provider` (`provider`) USING BTREE,
  KEY `idx_active` (`active`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Copiando dados para a tabela guilhermes_andressa_hospital.smtp_config: ~0 rows (aproximadamente)
INSERT INTO `smtp_config` (`id`, `provider`, `from_email`, `from_name`, `host`, `port`, `username`, `password_app`, `auth`, `starttls`, `ssl`, `active`, `created_at`, `updated_at`) VALUES
	(1, 'Gmail', 'andressaoliveira60812@gmail.com', 'Hospital Machado', 'smtp.gmail.com', 587, 'andressaoliveira60812@gmail.com', 'xjdd tlzl ixph txmm', 1, 1, 0, 1, '2025-08-29 17:57:28', '2025-08-29 17:57:28');

-- Copiando estrutura para tabela guilhermes_andressa_hospital.usuario
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nomeusuario` varchar(255) NOT NULL,
  `senhahash` varchar(60) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nomeusuario` (`nomeusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Copiando dados para a tabela guilhermes_andressa_hospital.usuario: ~6 rows (aproximadamente)
INSERT INTO `usuario` (`id`, `nomeusuario`, `senhahash`, `email`) VALUES
	(1, 'Guilherme', '$2a$10$2E/xjGRmM.wkQKtzHrf1Z.5flRJ36zbb1iQAFWiuB7SQyCMhG6JOm', 'guilherme.salles@alunos.ifsuldeminas.edu.br'),
	(5, 'GuilhermÃ£o', '$2a$10$f.gyHCGTn4GkG5csufOCMuzb7fsweJtt8xRWKiN04cx5xXx09aT7S', 'guilhermesalles796@gmail.com'),
	(6, 'Karla', '$2a$10$o5N0smZS9EBMxD2jxkwddOZX.qvqkIJg3gRx6tV72D0BZa0ARFTYG', 'karla@gmail.com'),
	(7, 'Fernanda', '$2a$10$qjRL3wLUfyzhkuKryq.xje7jLvEg73BhfUBFDZfZ9cPqK50FYOls2', 'andressaoliveira60812@gmail.com'),
	(8, 'Angelina', '$2a$10$J.h91yTmUlQgEI16uv8zeOcJTdGxuN3pzUIibcHQBrWMdKksSAnfy', 'andressa2.rodrigues@alunos.ifsuldeminas.edu.br'),
	(10, 'Josefina', '$2a$10$DarKvrMz6O2pQ6SslljHGOJqg4s/dH4ObL4XVeIm750y2RlE0tezS', 'guilherme.salles@alunos.ifsuldeminas.edu.br');

-- Copiando estrutura para trigger guilhermes_andressa_hospital.tri_atualizaAmbulatorio
DROP TRIGGER IF EXISTS `tri_atualizaAmbulatorio`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO';
DELIMITER //
CREATE TRIGGER `tri_atualizaAmbulatorio` BEFORE INSERT ON `ambulatorio_has_medicacao` FOR EACH ROW BEGIN
		UPDATE ambulatorio SET quantAmbulancias = quantAmbulancias - 1
		WHERE codAmbulatorio = NEW.ambulatorio_codAmbulatorio;
		SET @mensagem = CONCAT("Atualização na quantidade de ambulancias ao inserir, do código = ", NEW.ambulatorio_codAmbulatorio);
		INSERT INTO auditoria VALUES(NULL,@mensagem,"ambulatorio",NOW(),USER());
		
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Copiando estrutura para trigger guilhermes_andressa_hospital.tri_atualizaAmbulatorioAoAlterar
DROP TRIGGER IF EXISTS `tri_atualizaAmbulatorioAoAlterar`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO';
DELIMITER //
CREATE TRIGGER `tri_atualizaAmbulatorioAoAlterar` BEFORE UPDATE ON `ambulatorio_has_medicacao` FOR EACH ROW BEGIN
	UPDATE ambulatorio SET quantAmbulancias = quantAmbulancias - 1
		WHERE codAmbulatorio = NEW.ambulatorio_codAmbulatorio;
		SET @mensagem = CONCAT("Atualização na quantidade de ambulancias do código = ", NEW.ambulatorio_codAmbulatorio);
		INSERT INTO auditoria VALUES(NULL,@mensagem,"ambulatorio",NOW(),USER());
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Copiando estrutura para trigger guilhermes_andressa_hospital.tri_atualizaAoDeletarAmbulancia
DROP TRIGGER IF EXISTS `tri_atualizaAoDeletarAmbulancia`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO';
DELIMITER //
CREATE TRIGGER `tri_atualizaAoDeletarAmbulancia` BEFORE DELETE ON `ambulatorio_has_medicacao` FOR EACH ROW BEGIN
		UPDATE ambulatorio SET quantAmbulancias = quantAmbulancias + 1
		WHERE codAmbulatorio = OLD.ambulatorio_codAmbulatorio;
	
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Copiando estrutura para trigger guilhermes_andressa_hospital.tri_atualizaSalario
DROP TRIGGER IF EXISTS `tri_atualizaSalario`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `tri_atualizaSalario` BEFORE UPDATE ON `cargo` FOR EACH ROW BEGIN
if(NEW.bonificacao >= 0)
	then SET NEW.salarioInicial = OLD.salarioInicial + (OLD.salarioInicial * NEW.bonificacao/100);
	SET @mensagem = CONCAT("Salário do cargo = ", NEW.nomeCargo," atualizado para = ", NEW.salarioInicial);
	INSERT INTO auditoria VALUES(NULL, @mensagem, "cargo", NOW(), USER());
ELSE INSERT INTO auditoria VALUES(NULL, "Valor inválido para bonificação do cargo!", "cargo", NOW(), USER());
END if;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Copiando estrutura para trigger guilhermes_andressa_hospital.tri_atualizaValorMedicamento
DROP TRIGGER IF EXISTS `tri_atualizaValorMedicamento`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `tri_atualizaValorMedicamento` BEFORE UPDATE ON `medicacao` FOR EACH ROW BEGIN
if(NEW.desconto >= 0)
	then SET NEW.valorRemedio = OLD.valorRemedio - (OLD.valorRemedio * NEW.desconto/100);
	SET @mensagem = CONCAT("Valor do remédio = ", NEW.nomeRemedio," atualizado para = ", NEW.valorRemedio," devido a descontos!");
	INSERT INTO auditoria VALUES(NULL, @mensagem, "medicacao", NOW(), USER());
ELSE INSERT INTO auditoria VALUES(NULL, "Valor inválido para desconto no medicamento!", "medicacao", NOW(), USER());
END if;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
