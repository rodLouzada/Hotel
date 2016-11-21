-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 04-Out-2016 às 20:16
-- Versão do servidor: 10.1.13-MariaDB
-- PHP Version: 5.6.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotel`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `caract`
--

CREATE TABLE `caract` (
  `cod` int(11) NOT NULL,
  `nome` text NOT NULL,
  `descricao` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `caract`
--

INSERT INTO `caract` (`cod`, `nome`, `descricao`) VALUES
(2, 'Ar Condicionado', ''),
(3, 'Banheira', ''),
(4, 'Ventilador', ''),
(5, 'Internet - Cabo', ''),
(6, 'Internet - Wifi', ''),
(7, 'Frigobar', ''),
(8, 'Cama de Casal', ''),
(9, 'Cama de Solteiro', ''),
(10, 'Banheiro', '');

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `cod` int(60) NOT NULL,
  `nome` text NOT NULL,
  `rua` text NOT NULL,
  `numero` int(4) NOT NULL,
  `cidade` text NOT NULL,
  `uf` text NOT NULL,
  `cep` text,
  `cpf` text NOT NULL,
  `identidade` text NOT NULL,
  `telefone` text NOT NULL,
  `email` text,
  `dataNasc` date DEFAULT NULL,
  `hospedado` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`cod`, `nome`, `rua`, `numero`, `cidade`, `uf`, `cep`, `cpf`, `identidade`, `telefone`, `email`, `dataNasc`, `hospedado`) VALUES
(3, 'Brenda Peixinho', 'Rua 3', 333, 'Coronel', 'MG', '33333-333', '333.333.333-33', 'CC-33.333.333', '(33)3333-3333', 'brenda@hotmail.com', '1994-03-09', 'true'),
(4, 'Guilherme Lucas', 'Rua Quetúnia, Esperança', 255, 'Ipatinga', 'MG', '35162-341', '115.421.276-96', 'MG-17.781.124', '(31)3824-7416', 'gui_lucas22@hotmail.com', '1994-07-23', 'true'),
(5, 'Williane Soares', 'Bom Jardim, rua Laura', 136, 'Ipatinga', 'MG', '35160-000', '123.123.123-23', 'MG-12.312.314', '(31)3826-2021', 'wiliane.scoelho@hotmail.com', '2002-07-22', 'false'),
(7, 'Rhaynara', 'Rua ', 154, 'Timoteo', 'MG', '31565-659', '117.755.176-40', 'MG-15.425.697', '(31)3824-7166', '', '1994-09-08', '0'),
(8, 'Josimar', 'Rua tal', 125, 'Ipatinga', 'MG', '35162-341', '123.456.789-09', 'MG-11.111.111', '(31)3854-5658', '', '1994-07-23', '0');

-- --------------------------------------------------------

--
-- Estrutura da tabela `consumo`
--

CREATE TABLE `consumo` (
  `cod` int(11) NOT NULL,
  `servico_cod` int(11) DEFAULT NULL,
  `produto_cod` int(11) DEFAULT NULL,
  `conta_cod` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `dataConsumo` date NOT NULL,
  `valorUnitario` double NOT NULL,
  `valorTotal` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `consumo`
--

INSERT INTO `consumo` (`cod`, `servico_cod`, `produto_cod`, `conta_cod`, `quantidade`, `dataConsumo`, `valorUnitario`, `valorTotal`) VALUES
(1, NULL, 1, 2, 1, '2011-11-02', 15.9, 15.9),
(2, 1, NULL, 2, 1, '2011-11-02', 30, 30),
(3, NULL, 2, 2, 2, '2011-11-02', 25.9, 51.8),
(4, NULL, 2, 3, 1, '2011-11-06', 25.9, 25.9),
(5, 1, NULL, 3, 2, '2011-11-06', 30, 60),
(6, 1, NULL, 4, 1, '2011-11-21', 30, 30),
(7, NULL, 2, 4, 2, '2011-11-21', 25.9, 51.8);

-- --------------------------------------------------------

--
-- Estrutura da tabela `conta`
--

CREATE TABLE `conta` (
  `cod` int(11) NOT NULL,
  `hospedagem_cod` int(11) NOT NULL,
  `valorTotal` double NOT NULL,
  `pago` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `conta`
--

INSERT INTO `conta` (`cod`, `hospedagem_cod`, `valorTotal`, `pago`) VALUES
(2, 2, 97.7, 'false'),
(3, 2, 85.9, 'false'),
(4, 3, 81.8, 'false');

-- --------------------------------------------------------

--
-- Estrutura da tabela `hospedagem`
--

CREATE TABLE `hospedagem` (
  `cod` int(11) NOT NULL,
  `cliente_cod` int(11) NOT NULL,
  `dataSaida` date DEFAULT NULL,
  `dataEntrada` date DEFAULT NULL,
  `dataPrevistaSaida` date DEFAULT NULL,
  `dataPrevistaEntrada` date DEFAULT NULL,
  `checkout` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `hospedagem`
--

INSERT INTO `hospedagem` (`cod`, `cliente_cod`, `dataSaida`, `dataEntrada`, `dataPrevistaSaida`, `dataPrevistaEntrada`, `checkout`) VALUES
(2, 4, '0000-00-00', '2011-11-02', '2011-11-06', '0000-00-00', 'false'),
(3, 3, '0000-00-00', '2011-11-06', '2011-11-10', '0000-00-00', 'false');

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--

CREATE TABLE `produto` (
  `cod` int(11) NOT NULL,
  `nome` text NOT NULL,
  `valor` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `produto`
--

INSERT INTO `produto` (`cod`, `nome`, `valor`) VALUES
(1, 'Café da manhã', 15.9),
(2, 'Café da manhã - Completo', 25.9),
(3, 'Coca Cola 500 ml', 4),
(4, 'Chockito', 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `quarto`
--

CREATE TABLE `quarto` (
  `cod` int(11) NOT NULL,
  `numero` int(11) NOT NULL,
  `valorDiaria` double NOT NULL,
  `ocupado` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `quarto`
--

INSERT INTO `quarto` (`cod`, `numero`, `valorDiaria`, `ocupado`) VALUES
(1, 101, 80, 'false'),
(2, 102, 100, 'true'),
(3, 105, 100, 'true'),
(4, 106, 200, 'true'),
(5, 302, 60, 'false');

-- --------------------------------------------------------

--
-- Estrutura da tabela `quarto_caract`
--

CREATE TABLE `quarto_caract` (
  `quarto_cod` int(11) NOT NULL,
  `caract_cod` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `quarto_caract`
--

INSERT INTO `quarto_caract` (`quarto_cod`, `caract_cod`, `quantidade`) VALUES
(1, 9, 1),
(1, 9, 1),
(1, 9, 1),
(2, 4, 2),
(2, 4, 2),
(2, 4, 2),
(2, 4, 2),
(2, 4, 2),
(2, 4, 2),
(3, 8, 1),
(3, 2, 1),
(3, 3, 1),
(4, 2, 1),
(4, 3, 1),
(4, 5, 1),
(4, 6, 1),
(4, 7, 1),
(4, 8, 1),
(4, 9, 2),
(5, 10, 3),
(5, 8, 2),
(5, 9, 6),
(5, 4, 4);

-- --------------------------------------------------------

--
-- Estrutura da tabela `quarto_hospedagem`
--

CREATE TABLE `quarto_hospedagem` (
  `hospedagem_cod` int(11) NOT NULL,
  `quarto_cod` int(11) NOT NULL,
  `qtdPessoa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `quarto_hospedagem`
--

INSERT INTO `quarto_hospedagem` (`hospedagem_cod`, `quarto_cod`, `qtdPessoa`) VALUES
(2, 2, 2),
(2, 4, 3),
(3, 4, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `servico`
--

CREATE TABLE `servico` (
  `cod` int(11) NOT NULL,
  `nome` text NOT NULL,
  `valor` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `servico`
--

INSERT INTO `servico` (`cod`, `nome`, `valor`) VALUES
(1, 'Serviço de Quarto', 30);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE `usuario` (
  `cod` int(60) NOT NULL,
  `nome` text NOT NULL,
  `login` text NOT NULL,
  `senha` text NOT NULL,
  `pergunta` text NOT NULL,
  `resposta` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`cod`, `nome`, `login`, `senha`, `pergunta`, `resposta`) VALUES
(1, 'Guilherme', 'gui', '123', '', ''),
(3, 'Laureta', 'lau', '321', '', ''),
(4, 'Ana', 'ana', '123', 'Nome do meu cachorro', 'thor'),
(5, 'Laura Braga Araujo', 'laurinha', '123', 'Qual nome do pet?', 'dog');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `caract`
--
ALTER TABLE `caract`
  ADD PRIMARY KEY (`cod`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`cod`);

--
-- Indexes for table `consumo`
--
ALTER TABLE `consumo`
  ADD PRIMARY KEY (`cod`),
  ADD KEY `servico_cod` (`servico_cod`,`produto_cod`,`conta_cod`),
  ADD KEY `produto_cod` (`produto_cod`),
  ADD KEY `conta_cod` (`conta_cod`);

--
-- Indexes for table `conta`
--
ALTER TABLE `conta`
  ADD PRIMARY KEY (`cod`),
  ADD KEY `codHospedagem` (`hospedagem_cod`);

--
-- Indexes for table `hospedagem`
--
ALTER TABLE `hospedagem`
  ADD PRIMARY KEY (`cod`),
  ADD KEY `cliente_cod` (`cliente_cod`);

--
-- Indexes for table `produto`
--
ALTER TABLE `produto`
  ADD PRIMARY KEY (`cod`);

--
-- Indexes for table `quarto`
--
ALTER TABLE `quarto`
  ADD PRIMARY KEY (`cod`);

--
-- Indexes for table `quarto_caract`
--
ALTER TABLE `quarto_caract`
  ADD KEY `quarto_cod` (`quarto_cod`,`caract_cod`),
  ADD KEY `caract_cod` (`caract_cod`);

--
-- Indexes for table `quarto_hospedagem`
--
ALTER TABLE `quarto_hospedagem`
  ADD KEY `hospedagem_cod` (`hospedagem_cod`,`quarto_cod`),
  ADD KEY `quarto_cod` (`quarto_cod`);

--
-- Indexes for table `servico`
--
ALTER TABLE `servico`
  ADD PRIMARY KEY (`cod`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`cod`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `caract`
--
ALTER TABLE `caract`
  MODIFY `cod` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `cod` int(60) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `consumo`
--
ALTER TABLE `consumo`
  MODIFY `cod` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `conta`
--
ALTER TABLE `conta`
  MODIFY `cod` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `hospedagem`
--
ALTER TABLE `hospedagem`
  MODIFY `cod` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `produto`
--
ALTER TABLE `produto`
  MODIFY `cod` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `quarto`
--
ALTER TABLE `quarto`
  MODIFY `cod` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `servico`
--
ALTER TABLE `servico`
  MODIFY `cod` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `cod` int(60) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `consumo`
--
ALTER TABLE `consumo`
  ADD CONSTRAINT `consumo_ibfk_1` FOREIGN KEY (`servico_cod`) REFERENCES `servico` (`cod`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `consumo_ibfk_2` FOREIGN KEY (`produto_cod`) REFERENCES `produto` (`cod`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `consumo_ibfk_3` FOREIGN KEY (`conta_cod`) REFERENCES `conta` (`cod`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `conta`
--
ALTER TABLE `conta`
  ADD CONSTRAINT `conta_ibfk_1` FOREIGN KEY (`hospedagem_cod`) REFERENCES `hospedagem` (`cod`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `hospedagem`
--
ALTER TABLE `hospedagem`
  ADD CONSTRAINT `hospedagem_ibfk_1` FOREIGN KEY (`cliente_cod`) REFERENCES `cliente` (`cod`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `quarto_caract`
--
ALTER TABLE `quarto_caract`
  ADD CONSTRAINT `quarto_caract_ibfk_1` FOREIGN KEY (`quarto_cod`) REFERENCES `quarto` (`cod`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `quarto_caract_ibfk_2` FOREIGN KEY (`caract_cod`) REFERENCES `caract` (`cod`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `quarto_hospedagem`
--
ALTER TABLE `quarto_hospedagem`
  ADD CONSTRAINT `quarto_hospedagem_ibfk_1` FOREIGN KEY (`hospedagem_cod`) REFERENCES `hospedagem` (`cod`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `quarto_hospedagem_ibfk_2` FOREIGN KEY (`quarto_cod`) REFERENCES `quarto` (`cod`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
