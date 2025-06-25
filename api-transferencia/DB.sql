USE [master]
GO
/****** Object:  Database [db_account]    Script Date: 24/06/2025 20:40:35 ******/
CREATE DATABASE [db_account]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'db_account', FILENAME = N'/var/opt/mssql/data/db_account.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'db_account_log', FILENAME = N'/var/opt/mssql/data/db_account_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [db_account] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [db_account].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [db_account] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [db_account] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [db_account] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [db_account] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [db_account] SET ARITHABORT OFF 
GO
ALTER DATABASE [db_account] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [db_account] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [db_account] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [db_account] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [db_account] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [db_account] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [db_account] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [db_account] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [db_account] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [db_account] SET  ENABLE_BROKER 
GO
ALTER DATABASE [db_account] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [db_account] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [db_account] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [db_account] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [db_account] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [db_account] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [db_account] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [db_account] SET RECOVERY FULL 
GO
ALTER DATABASE [db_account] SET  MULTI_USER 
GO
ALTER DATABASE [db_account] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [db_account] SET DB_CHAINING OFF 
GO
ALTER DATABASE [db_account] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [db_account] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [db_account] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [db_account] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'db_account', N'ON'
GO
ALTER DATABASE [db_account] SET QUERY_STORE = OFF
GO
USE [db_account]
GO
/****** Object:  Table [dbo].[account]    Script Date: 24/06/2025 20:40:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[account](
	[id_account] [int] IDENTITY(1,1) NOT NULL,
	[total_ammount] [decimal](10, 2) NULL,
	[id_customer] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_account] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[clientes]    Script Date: 24/06/2025 20:40:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[clientes](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nombres] [varchar](100) NOT NULL,
	[apellidos] [varchar](100) NOT NULL,
	[email] [varchar](100) NULL,
	[direccion] [varchar](255) NULL,
	[fecha_creacion] [datetime] NULL,
 CONSTRAINT [pk_clientes] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[customer]    Script Date: 24/06/2025 20:40:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[customer](
	[id_customer] [int] IDENTITY(1,1) NOT NULL,
	[full_name] [varchar](200) NULL,
	[nombres] [varchar](100) NULL,
	[apellidos] [varchar](100) NULL,
	[email] [varchar](100) NULL,
	[direccion] [varchar](255) NULL,
	[fecha_creacion] [datetime] NULL,
	[fecha_actualizacion] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_customer] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[pedido_detalles]    Script Date: 24/06/2025 20:40:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[pedido_detalles](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[pedido_id] [int] NOT NULL,
	[producto_id] [int] NOT NULL,
	[cantidad] [decimal](15, 3) NOT NULL,
	[subtotal] [decimal](15, 5) NOT NULL,
	[fecha_creacion] [datetime] NULL,
 CONSTRAINT [pk_pedido_detalles] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[pedidos]    Script Date: 24/06/2025 20:40:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[pedidos](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[cliente_id] [int] NOT NULL,
	[fecha] [date] NOT NULL,
	[estado] [varchar](30) NOT NULL,
	[total] [decimal](15, 5) NOT NULL,
	[fecha_creacion] [datetime] NULL,
 CONSTRAINT [pk_pedidos] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[productos]    Script Date: 24/06/2025 20:40:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[productos](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](255) NOT NULL,
	[precio_unitario] [decimal](15, 3) NOT NULL,
	[stock] [decimal](15, 5) NOT NULL,
	[fecha_creacion] [datetime] NULL,
 CONSTRAINT [pk_productos] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[transaction]    Script Date: 24/06/2025 20:40:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[transaction](
	[id_transaction] [int] IDENTITY(1,1) NOT NULL,
	[amount] [decimal](18, 2) NOT NULL,
	[type] [varchar](50) NULL,
	[fecha_creacion] [datetime] NULL,
	[id_account] [int] NOT NULL,
	[current_amount] [decimal](18, 2) NULL,
	[previous_amount] [decimal](18, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_transaction] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[account] ON 
GO
INSERT [dbo].[account] ([id_account], [total_ammount], [id_customer]) VALUES (1, CAST(8589.10 AS Decimal(10, 2)), 1)
GO
INSERT [dbo].[account] ([id_account], [total_ammount], [id_customer]) VALUES (2, CAST(1962.00 AS Decimal(10, 2)), 2)
GO
INSERT [dbo].[account] ([id_account], [total_ammount], [id_customer]) VALUES (3, CAST(9820.75 AS Decimal(10, 2)), 3)
GO
INSERT [dbo].[account] ([id_account], [total_ammount], [id_customer]) VALUES (4, CAST(175.20 AS Decimal(10, 2)), 4)
GO
INSERT [dbo].[account] ([id_account], [total_ammount], [id_customer]) VALUES (5, CAST(5000.00 AS Decimal(10, 2)), 5)
GO
INSERT [dbo].[account] ([id_account], [total_ammount], [id_customer]) VALUES (6, CAST(245.00 AS Decimal(10, 2)), 6)
GO
INSERT [dbo].[account] ([id_account], [total_ammount], [id_customer]) VALUES (7, CAST(1350.40 AS Decimal(10, 2)), 7)
GO
INSERT [dbo].[account] ([id_account], [total_ammount], [id_customer]) VALUES (8, CAST(900.00 AS Decimal(10, 2)), 8)
GO
INSERT [dbo].[account] ([id_account], [total_ammount], [id_customer]) VALUES (9, CAST(6780.10 AS Decimal(10, 2)), 9)
GO
INSERT [dbo].[account] ([id_account], [total_ammount], [id_customer]) VALUES (10, CAST(85000.00 AS Decimal(10, 2)), 10)
GO
INSERT [dbo].[account] ([id_account], [total_ammount], [id_customer]) VALUES (11, CAST(430.25 AS Decimal(10, 2)), 11)
GO
INSERT [dbo].[account] ([id_account], [total_ammount], [id_customer]) VALUES (12, CAST(7800.00 AS Decimal(10, 2)), 12)
GO
INSERT [dbo].[account] ([id_account], [total_ammount], [id_customer]) VALUES (14, CAST(1500.00 AS Decimal(10, 2)), 14)
GO
INSERT [dbo].[account] ([id_account], [total_ammount], [id_customer]) VALUES (1004, CAST(13520.00 AS Decimal(10, 2)), 2)
GO
INSERT [dbo].[account] ([id_account], [total_ammount], [id_customer]) VALUES (1007, CAST(14000.00 AS Decimal(10, 2)), 2)
GO
INSERT [dbo].[account] ([id_account], [total_ammount], [id_customer]) VALUES (2002, CAST(150000.00 AS Decimal(10, 2)), 2005)
GO
SET IDENTITY_INSERT [dbo].[account] OFF
GO
SET IDENTITY_INSERT [dbo].[clientes] ON 
GO
INSERT [dbo].[clientes] ([id], [nombres], [apellidos], [email], [direccion], [fecha_creacion]) VALUES (1, N'Laura', N'Gomez', NULL, N'Calle 123, Lima', CAST(N'2024-01-12T17:22:55.000' AS DateTime))
GO
INSERT [dbo].[clientes] ([id], [nombres], [apellidos], [email], [direccion], [fecha_creacion]) VALUES (2, N'Jose', N'Lopez', NULL, N'Av. Independencia 456, Cusco', CAST(N'2023-11-22T10:41:33.000' AS DateTime))
GO
INSERT [dbo].[clientes] ([id], [nombres], [apellidos], [email], [direccion], [fecha_creacion]) VALUES (3, N'Ana', N'Sanchez', NULL, N'Jr. Arequipa 789, Trujillo', CAST(N'2024-04-15T19:30:04.000' AS DateTime))
GO
INSERT [dbo].[clientes] ([id], [nombres], [apellidos], [email], [direccion], [fecha_creacion]) VALUES (4, N'Marco', N'Perez', NULL, N'Urb. Santa Anita, Arequipa', CAST(N'2023-09-30T08:50:11.000' AS DateTime))
GO
INSERT [dbo].[clientes] ([id], [nombres], [apellidos], [email], [direccion], [fecha_creacion]) VALUES (5, N'Lucia', N'Vargas', NULL, N'Mz B Lote 15, Chiclayo', CAST(N'2023-07-24T13:23:08.000' AS DateTime))
GO
INSERT [dbo].[clientes] ([id], [nombres], [apellidos], [email], [direccion], [fecha_creacion]) VALUES (6, N'Carlos', N'Ramirez', NULL, N'Av. Salaverry 101, Tacna', CAST(N'2024-03-03T10:33:52.000' AS DateTime))
GO
INSERT [dbo].[clientes] ([id], [nombres], [apellidos], [email], [direccion], [fecha_creacion]) VALUES (7, N'Diana', N'Torres', NULL, N'Calle Las Flores, Huancayo', CAST(N'2023-10-19T09:18:43.000' AS DateTime))
GO
INSERT [dbo].[clientes] ([id], [nombres], [apellidos], [email], [direccion], [fecha_creacion]) VALUES (8, N'Luis', N'Diaz', NULL, N'Jr. Amazonas 202, Iquitos', CAST(N'2023-08-07T12:09:01.000' AS DateTime))
GO
INSERT [dbo].[clientes] ([id], [nombres], [apellidos], [email], [direccion], [fecha_creacion]) VALUES (9, N'Patricia', N'Reyes', NULL, N'Calle Los Rosales, Tumbes', CAST(N'2024-01-28T14:44:19.000' AS DateTime))
GO
INSERT [dbo].[clientes] ([id], [nombres], [apellidos], [email], [direccion], [fecha_creacion]) VALUES (10, N'Miguel', N'Herrera', NULL, N'Av. Grau 77, Cajamarca', CAST(N'2024-02-12T16:00:00.000' AS DateTime))
GO
INSERT [dbo].[clientes] ([id], [nombres], [apellidos], [email], [direccion], [fecha_creacion]) VALUES (1002, N'AAAAA', N'AAAAA', N'AAAAA', N'AAAAAAA', CAST(N'2025-06-21T03:26:04.610' AS DateTime))
GO
INSERT [dbo].[clientes] ([id], [nombres], [apellidos], [email], [direccion], [fecha_creacion]) VALUES (1003, N'BBBBB', N'BBBB', N'BBB', N'BBB', CAST(N'2025-06-21T03:26:04.623' AS DateTime))
GO
INSERT [dbo].[clientes] ([id], [nombres], [apellidos], [email], [direccion], [fecha_creacion]) VALUES (1004, N'CCCC', N'CCC', N'CCCC', N'CCC', CAST(N'2025-06-21T03:26:04.633' AS DateTime))
GO
SET IDENTITY_INSERT [dbo].[clientes] OFF
GO
SET IDENTITY_INSERT [dbo].[customer] ON 
GO
INSERT [dbo].[customer] ([id_customer], [full_name], [nombres], [apellidos], [email], [direccion], [fecha_creacion], [fecha_actualizacion]) VALUES (1, N'Juan Pérez', N'Juan', N'Pérez', N'juan.pérez@example.com', N'Av. Arequipa 123, Lima', CAST(N'2025-06-21T20:09:21.483' AS DateTime), NULL)
GO
INSERT [dbo].[customer] ([id_customer], [full_name], [nombres], [apellidos], [email], [direccion], [fecha_creacion], [fecha_actualizacion]) VALUES (2, N'María Gómez', N'María', N'Gómez', N'maría.gómez@example.com', N'Jr. Puno 456, Cusco', CAST(N'2025-06-21T20:09:21.483' AS DateTime), NULL)
GO
INSERT [dbo].[customer] ([id_customer], [full_name], [nombres], [apellidos], [email], [direccion], [fecha_creacion], [fecha_actualizacion]) VALUES (3, N'Carlos Sánchez', N'Carlos', N'Sánchez', N'carlos.sánchez@example.com', N'Av. Venezuela 789, Arequipa', CAST(N'2025-06-21T20:09:21.483' AS DateTime), NULL)
GO
INSERT [dbo].[customer] ([id_customer], [full_name], [nombres], [apellidos], [email], [direccion], [fecha_creacion], [fecha_actualizacion]) VALUES (4, N'Luisa Martínez', N'Luisa', N'Martínez', N'luisa.martínez@example.com', N'Calle Los Álamos 321, Trujillo', CAST(N'2025-06-21T20:09:21.483' AS DateTime), NULL)
GO
INSERT [dbo].[customer] ([id_customer], [full_name], [nombres], [apellidos], [email], [direccion], [fecha_creacion], [fecha_actualizacion]) VALUES (5, N'Pedro Torres', N'Pedro', N'Torres', N'pedro.torres@example.com', N'Av. Grau 987, Piura', CAST(N'2025-06-21T20:09:21.483' AS DateTime), NULL)
GO
INSERT [dbo].[customer] ([id_customer], [full_name], [nombres], [apellidos], [email], [direccion], [fecha_creacion], [fecha_actualizacion]) VALUES (6, N'Ana Rodríguez', N'Ana', N'Rodríguez', N'ana.rodríguez@example.com', N'Jr. Tacna 555, Chiclayo', CAST(N'2025-06-21T20:09:21.483' AS DateTime), NULL)
GO
INSERT [dbo].[customer] ([id_customer], [full_name], [nombres], [apellidos], [email], [direccion], [fecha_creacion], [fecha_actualizacion]) VALUES (7, N'Sofía Ramírez', N'Sofía', N'Ramírez', N'sofía.ramírez@example.com', N'Av. Brasil 111, Lima', CAST(N'2025-06-21T20:09:21.483' AS DateTime), NULL)
GO
INSERT [dbo].[customer] ([id_customer], [full_name], [nombres], [apellidos], [email], [direccion], [fecha_creacion], [fecha_actualizacion]) VALUES (8, N'Daniel Fernández', N'Daniel', N'Fernández', N'daniel.fernández@example.com', N'Calle Lima 222, Cajamarca', CAST(N'2025-06-21T20:09:21.483' AS DateTime), NULL)
GO
INSERT [dbo].[customer] ([id_customer], [full_name], [nombres], [apellidos], [email], [direccion], [fecha_creacion], [fecha_actualizacion]) VALUES (9, N'Laura Vargas', N'Laura', N'Vargas', N'laura.vargas@example.com', N'Av. Salaverry 333, Huancayo', CAST(N'2025-06-21T20:09:21.483' AS DateTime), NULL)
GO
INSERT [dbo].[customer] ([id_customer], [full_name], [nombres], [apellidos], [email], [direccion], [fecha_creacion], [fecha_actualizacion]) VALUES (10, N'Jorge Castillo', N'Jorge', N'Castillo', N'jorge.castillo@example.com', N'Calle Libertad 444, Ica', CAST(N'2025-06-21T20:09:21.483' AS DateTime), NULL)
GO
INSERT [dbo].[customer] ([id_customer], [full_name], [nombres], [apellidos], [email], [direccion], [fecha_creacion], [fecha_actualizacion]) VALUES (11, N'Isabela Moreno', N'Isabela', N'Moreno', N'isabela.moreno@example.com', N'Jr. Loreto 666, Tarapoto', CAST(N'2025-06-21T20:09:21.483' AS DateTime), NULL)
GO
INSERT [dbo].[customer] ([id_customer], [full_name], [nombres], [apellidos], [email], [direccion], [fecha_creacion], [fecha_actualizacion]) VALUES (12, N'Andrés Salazar', N'Andrés', N'Salazar', N'andrés.salazar@example.com', N'Av. Los Incas 777, Cusco', CAST(N'2025-06-21T20:09:21.483' AS DateTime), NULL)
GO
INSERT [dbo].[customer] ([id_customer], [full_name], [nombres], [apellidos], [email], [direccion], [fecha_creacion], [fecha_actualizacion]) VALUES (14, N'Fernando López', N'Fernando', N'López', N'fernando.lópez@example.com', N'Av. El Sol 999, Puno', CAST(N'2025-06-21T20:09:21.483' AS DateTime), NULL)
GO
INSERT [dbo].[customer] ([id_customer], [full_name], [nombres], [apellidos], [email], [direccion], [fecha_creacion], [fecha_actualizacion]) VALUES (15, N'Camila Lujan Herrera', N'Camila', N'Lujan Herrera', N'camila.lujanherrera@gmail.com', N'Jr. Junín 100, Ayacucho', CAST(N'2025-06-21T20:09:21.483' AS DateTime), CAST(N'2025-06-22T14:29:49.217' AS DateTime))
GO
INSERT [dbo].[customer] ([id_customer], [full_name], [nombres], [apellidos], [email], [direccion], [fecha_creacion], [fecha_actualizacion]) VALUES (2004, N'Juan Adan Quiroz Neyra', N'Juan Adan', N'Quiroz Neyra', N'juan.quirozneyra@gmail.com', N'Av. las gaviotas, Chorrillos - lima', CAST(N'2025-06-21T15:55:49.417' AS DateTime), CAST(N'2025-06-21T16:14:31.267' AS DateTime))
GO
INSERT [dbo].[customer] ([id_customer], [full_name], [nombres], [apellidos], [email], [direccion], [fecha_creacion], [fecha_actualizacion]) VALUES (2005, N'Mariana Tovar Cruz', N'Mariana', N'Tovar Cruz', N'marian.tovarcruz@gmail.com', N'urb. los girasoles de surco 123', CAST(N'2025-06-22T14:44:07.467' AS DateTime), NULL)
GO
SET IDENTITY_INSERT [dbo].[customer] OFF
GO
SET IDENTITY_INSERT [dbo].[productos] ON 
GO
INSERT [dbo].[productos] ([id], [nombre], [precio_unitario], [stock], [fecha_creacion]) VALUES (1, N'Shoes', CAST(276.230 AS Decimal(15, 3)), CAST(10.00000 AS Decimal(15, 5)), CAST(N'2025-06-20T05:39:54.223' AS DateTime))
GO
INSERT [dbo].[productos] ([id], [nombre], [precio_unitario], [stock], [fecha_creacion]) VALUES (2, N'Clock', CAST(136.990 AS Decimal(15, 3)), CAST(20.00000 AS Decimal(15, 5)), CAST(N'2025-06-20T05:39:54.233' AS DateTime))
GO
INSERT [dbo].[productos] ([id], [nombre], [precio_unitario], [stock], [fecha_creacion]) VALUES (3, N'Watch', CAST(195.840 AS Decimal(15, 3)), CAST(10.00000 AS Decimal(15, 5)), CAST(N'2025-06-20T05:39:54.237' AS DateTime))
GO
INSERT [dbo].[productos] ([id], [nombre], [precio_unitario], [stock], [fecha_creacion]) VALUES (4, N'Laptop', CAST(499.990 AS Decimal(15, 3)), CAST(10.00000 AS Decimal(15, 5)), CAST(N'2025-06-20T05:39:54.243' AS DateTime))
GO
INSERT [dbo].[productos] ([id], [nombre], [precio_unitario], [stock], [fecha_creacion]) VALUES (5, N'Phone', CAST(325.500 AS Decimal(15, 3)), CAST(10.00000 AS Decimal(15, 5)), CAST(N'2025-06-20T05:39:54.250' AS DateTime))
GO
INSERT [dbo].[productos] ([id], [nombre], [precio_unitario], [stock], [fecha_creacion]) VALUES (6, N'Tablet', CAST(220.300 AS Decimal(15, 3)), CAST(12.00000 AS Decimal(15, 5)), CAST(N'2025-06-20T05:39:54.253' AS DateTime))
GO
INSERT [dbo].[productos] ([id], [nombre], [precio_unitario], [stock], [fecha_creacion]) VALUES (7, N'Headphones', CAST(89.750 AS Decimal(15, 3)), CAST(18.00000 AS Decimal(15, 5)), CAST(N'2025-06-20T05:39:54.260' AS DateTime))
GO
INSERT [dbo].[productos] ([id], [nombre], [precio_unitario], [stock], [fecha_creacion]) VALUES (8, N'Monitor', CAST(130.200 AS Decimal(15, 3)), CAST(15.00000 AS Decimal(15, 5)), CAST(N'2025-06-20T05:39:54.263' AS DateTime))
GO
INSERT [dbo].[productos] ([id], [nombre], [precio_unitario], [stock], [fecha_creacion]) VALUES (9, N'Keyboard', CAST(55.990 AS Decimal(15, 3)), CAST(10.00000 AS Decimal(15, 5)), CAST(N'2025-06-20T05:39:54.270' AS DateTime))
GO
INSERT [dbo].[productos] ([id], [nombre], [precio_unitario], [stock], [fecha_creacion]) VALUES (10, N'Mouse', CAST(34.890 AS Decimal(15, 3)), CAST(10.00000 AS Decimal(15, 5)), CAST(N'2025-06-20T05:39:54.273' AS DateTime))
GO
SET IDENTITY_INSERT [dbo].[productos] OFF
GO
SET IDENTITY_INSERT [dbo].[transaction] ON 
GO
INSERT [dbo].[transaction] ([id_transaction], [amount], [type], [fecha_creacion], [id_account], [current_amount], [previous_amount]) VALUES (1, CAST(250.00 AS Decimal(18, 2)), N'deposit', CAST(N'2025-06-21T21:50:00.970' AS DateTime), 1, CAST(15570.50 AS Decimal(18, 2)), CAST(15320.50 AS Decimal(18, 2)))
GO
INSERT [dbo].[transaction] ([id_transaction], [amount], [type], [fecha_creacion], [id_account], [current_amount], [previous_amount]) VALUES (2, CAST(550.00 AS Decimal(18, 2)), N'deposit', CAST(N'2025-06-21T21:50:19.377' AS DateTime), 1, CAST(16120.50 AS Decimal(18, 2)), CAST(15570.50 AS Decimal(18, 2)))
GO
INSERT [dbo].[transaction] ([id_transaction], [amount], [type], [fecha_creacion], [id_account], [current_amount], [previous_amount]) VALUES (3, CAST(550.20 AS Decimal(18, 2)), N'deposit', CAST(N'2025-06-21T21:50:23.103' AS DateTime), 1, CAST(16670.70 AS Decimal(18, 2)), CAST(16120.50 AS Decimal(18, 2)))
GO
INSERT [dbo].[transaction] ([id_transaction], [amount], [type], [fecha_creacion], [id_account], [current_amount], [previous_amount]) VALUES (4, CAST(150.20 AS Decimal(18, 2)), N'deposit', CAST(N'2025-06-21T21:50:25.210' AS DateTime), 1, CAST(16820.90 AS Decimal(18, 2)), CAST(16670.70 AS Decimal(18, 2)))
GO
INSERT [dbo].[transaction] ([id_transaction], [amount], [type], [fecha_creacion], [id_account], [current_amount], [previous_amount]) VALUES (5, CAST(650.20 AS Decimal(18, 2)), N'deposit', CAST(N'2025-06-21T21:50:27.730' AS DateTime), 1, CAST(17471.10 AS Decimal(18, 2)), CAST(16820.90 AS Decimal(18, 2)))
GO
INSERT [dbo].[transaction] ([id_transaction], [amount], [type], [fecha_creacion], [id_account], [current_amount], [previous_amount]) VALUES (6, CAST(850.20 AS Decimal(18, 2)), N'deposit', CAST(N'2025-06-21T21:50:31.073' AS DateTime), 1, CAST(18321.30 AS Decimal(18, 2)), CAST(17471.10 AS Decimal(18, 2)))
GO
INSERT [dbo].[transaction] ([id_transaction], [amount], [type], [fecha_creacion], [id_account], [current_amount], [previous_amount]) VALUES (7, CAST(1.20 AS Decimal(18, 2)), N'deposit', CAST(N'2025-06-21T21:50:35.283' AS DateTime), 1, CAST(18322.50 AS Decimal(18, 2)), CAST(18321.30 AS Decimal(18, 2)))
GO
INSERT [dbo].[transaction] ([id_transaction], [amount], [type], [fecha_creacion], [id_account], [current_amount], [previous_amount]) VALUES (8, CAST(2.20 AS Decimal(18, 2)), N'deposit', CAST(N'2025-06-21T21:50:37.427' AS DateTime), 1, CAST(18324.70 AS Decimal(18, 2)), CAST(18322.50 AS Decimal(18, 2)))
GO
INSERT [dbo].[transaction] ([id_transaction], [amount], [type], [fecha_creacion], [id_account], [current_amount], [previous_amount]) VALUES (9, CAST(12.20 AS Decimal(18, 2)), N'deposit', CAST(N'2025-06-21T21:51:10.607' AS DateTime), 1, CAST(18336.90 AS Decimal(18, 2)), CAST(18324.70 AS Decimal(18, 2)))
GO
INSERT [dbo].[transaction] ([id_transaction], [amount], [type], [fecha_creacion], [id_account], [current_amount], [previous_amount]) VALUES (10, CAST(15.20 AS Decimal(18, 2)), N'deposit', CAST(N'2025-06-21T21:51:13.987' AS DateTime), 1, CAST(18352.10 AS Decimal(18, 2)), CAST(18336.90 AS Decimal(18, 2)))
GO
INSERT [dbo].[transaction] ([id_transaction], [amount], [type], [fecha_creacion], [id_account], [current_amount], [previous_amount]) VALUES (11, CAST(15.00 AS Decimal(18, 2)), N'deposit', CAST(N'2025-06-21T21:51:17.870' AS DateTime), 1, CAST(18367.10 AS Decimal(18, 2)), CAST(18352.10 AS Decimal(18, 2)))
GO
INSERT [dbo].[transaction] ([id_transaction], [amount], [type], [fecha_creacion], [id_account], [current_amount], [previous_amount]) VALUES (12, CAST(12.00 AS Decimal(18, 2)), N'deposit', CAST(N'2025-06-21T21:51:20.283' AS DateTime), 1, CAST(18379.10 AS Decimal(18, 2)), CAST(18367.10 AS Decimal(18, 2)))
GO
INSERT [dbo].[transaction] ([id_transaction], [amount], [type], [fecha_creacion], [id_account], [current_amount], [previous_amount]) VALUES (13, CAST(12.00 AS Decimal(18, 2)), N'deposit', CAST(N'2025-06-21T21:51:44.830' AS DateTime), 2, CAST(312.00 AS Decimal(18, 2)), CAST(300.00 AS Decimal(18, 2)))
GO
INSERT [dbo].[transaction] ([id_transaction], [amount], [type], [fecha_creacion], [id_account], [current_amount], [previous_amount]) VALUES (14, CAST(150.00 AS Decimal(18, 2)), N'deposit', CAST(N'2025-06-21T21:51:50.320' AS DateTime), 2, CAST(462.00 AS Decimal(18, 2)), CAST(312.00 AS Decimal(18, 2)))
GO
INSERT [dbo].[transaction] ([id_transaction], [amount], [type], [fecha_creacion], [id_account], [current_amount], [previous_amount]) VALUES (15, CAST(1500.00 AS Decimal(18, 2)), N'deposit', CAST(N'2025-06-21T21:51:53.170' AS DateTime), 2, CAST(1962.00 AS Decimal(18, 2)), CAST(462.00 AS Decimal(18, 2)))
GO
INSERT [dbo].[transaction] ([id_transaction], [amount], [type], [fecha_creacion], [id_account], [current_amount], [previous_amount]) VALUES (16, CAST(210.00 AS Decimal(18, 2)), N'deposit', CAST(N'2025-06-22T00:46:30.823' AS DateTime), 1, CAST(18589.10 AS Decimal(18, 2)), CAST(18379.10 AS Decimal(18, 2)))
GO
INSERT [dbo].[transaction] ([id_transaction], [amount], [type], [fecha_creacion], [id_account], [current_amount], [previous_amount]) VALUES (17, CAST(150.00 AS Decimal(18, 2)), N'deposit', CAST(N'2025-06-22T14:27:18.163' AS DateTime), 1, CAST(18739.10 AS Decimal(18, 2)), CAST(18589.10 AS Decimal(18, 2)))
GO
INSERT [dbo].[transaction] ([id_transaction], [amount], [type], [fecha_creacion], [id_account], [current_amount], [previous_amount]) VALUES (18, CAST(250.00 AS Decimal(18, 2)), N'deposit', CAST(N'2025-06-22T18:32:01.633' AS DateTime), 1, CAST(18989.10 AS Decimal(18, 2)), CAST(18739.10 AS Decimal(18, 2)))
GO
INSERT [dbo].[transaction] ([id_transaction], [amount], [type], [fecha_creacion], [id_account], [current_amount], [previous_amount]) VALUES (19, CAST(100.00 AS Decimal(18, 2)), N'deposit', CAST(N'2025-06-22T18:33:37.410' AS DateTime), 1, CAST(19089.10 AS Decimal(18, 2)), CAST(18989.10 AS Decimal(18, 2)))
GO
INSERT [dbo].[transaction] ([id_transaction], [amount], [type], [fecha_creacion], [id_account], [current_amount], [previous_amount]) VALUES (20, CAST(1000.00 AS Decimal(18, 2)), N'withdrawal', CAST(N'2025-06-22T18:36:24.153' AS DateTime), 1, CAST(18089.10 AS Decimal(18, 2)), CAST(19089.10 AS Decimal(18, 2)))
GO
INSERT [dbo].[transaction] ([id_transaction], [amount], [type], [fecha_creacion], [id_account], [current_amount], [previous_amount]) VALUES (21, CAST(1000.00 AS Decimal(18, 2)), N'deposit', CAST(N'2025-06-22T18:37:03.223' AS DateTime), 1, CAST(19089.10 AS Decimal(18, 2)), CAST(18089.10 AS Decimal(18, 2)))
GO
INSERT [dbo].[transaction] ([id_transaction], [amount], [type], [fecha_creacion], [id_account], [current_amount], [previous_amount]) VALUES (22, CAST(10000.00 AS Decimal(18, 2)), N'withdrawal', CAST(N'2025-06-22T18:37:31.913' AS DateTime), 1, CAST(9089.10 AS Decimal(18, 2)), CAST(19089.10 AS Decimal(18, 2)))
GO
INSERT [dbo].[transaction] ([id_transaction], [amount], [type], [fecha_creacion], [id_account], [current_amount], [previous_amount]) VALUES (23, CAST(10000.00 AS Decimal(18, 2)), N'deposit', CAST(N'2025-06-22T22:29:23.917' AS DateTime), 1, CAST(19089.10 AS Decimal(18, 2)), CAST(9089.10 AS Decimal(18, 2)))
GO
INSERT [dbo].[transaction] ([id_transaction], [amount], [type], [fecha_creacion], [id_account], [current_amount], [previous_amount]) VALUES (24, CAST(1000.00 AS Decimal(18, 2)), N'deposit', CAST(N'2025-06-23T05:17:42.060' AS DateTime), 1, CAST(20089.10 AS Decimal(18, 2)), CAST(19089.10 AS Decimal(18, 2)))
GO
INSERT [dbo].[transaction] ([id_transaction], [amount], [type], [fecha_creacion], [id_account], [current_amount], [previous_amount]) VALUES (25, CAST(20000.00 AS Decimal(18, 2)), N'withdrawal', CAST(N'2025-06-23T05:17:54.623' AS DateTime), 1, CAST(89.10 AS Decimal(18, 2)), CAST(20089.10 AS Decimal(18, 2)))
GO
INSERT [dbo].[transaction] ([id_transaction], [amount], [type], [fecha_creacion], [id_account], [current_amount], [previous_amount]) VALUES (1018, CAST(10000.00 AS Decimal(18, 2)), N'deposit', CAST(N'2025-06-24T01:14:29.197' AS DateTime), 1, CAST(10089.10 AS Decimal(18, 2)), CAST(89.10 AS Decimal(18, 2)))
GO
INSERT [dbo].[transaction] ([id_transaction], [amount], [type], [fecha_creacion], [id_account], [current_amount], [previous_amount]) VALUES (1019, CAST(1500.00 AS Decimal(18, 2)), N'withdrawal', CAST(N'2025-06-24T01:17:52.117' AS DateTime), 1, CAST(8589.10 AS Decimal(18, 2)), CAST(10089.10 AS Decimal(18, 2)))
GO
SET IDENTITY_INSERT [dbo].[transaction] OFF
GO
ALTER TABLE [dbo].[clientes] ADD  DEFAULT (getdate()) FOR [fecha_creacion]
GO
ALTER TABLE [dbo].[customer] ADD  DEFAULT (getdate()) FOR [fecha_creacion]
GO
ALTER TABLE [dbo].[customer] ADD  DEFAULT (getdate()) FOR [fecha_actualizacion]
GO
ALTER TABLE [dbo].[pedido_detalles] ADD  DEFAULT (getdate()) FOR [fecha_creacion]
GO
ALTER TABLE [dbo].[pedidos] ADD  DEFAULT ((0.0)) FOR [total]
GO
ALTER TABLE [dbo].[pedidos] ADD  DEFAULT (getdate()) FOR [fecha_creacion]
GO
ALTER TABLE [dbo].[productos] ADD  DEFAULT (getdate()) FOR [fecha_creacion]
GO
ALTER TABLE [dbo].[account]  WITH CHECK ADD  CONSTRAINT [FK_Account_Customer] FOREIGN KEY([id_customer])
REFERENCES [dbo].[customer] ([id_customer])
GO
ALTER TABLE [dbo].[account] CHECK CONSTRAINT [FK_Account_Customer]
GO
ALTER TABLE [dbo].[pedido_detalles]  WITH CHECK ADD  CONSTRAINT [fk_detalle_pedido] FOREIGN KEY([pedido_id])
REFERENCES [dbo].[pedidos] ([id])
GO
ALTER TABLE [dbo].[pedido_detalles] CHECK CONSTRAINT [fk_detalle_pedido]
GO
ALTER TABLE [dbo].[pedido_detalles]  WITH CHECK ADD  CONSTRAINT [fk_detalle_producto] FOREIGN KEY([producto_id])
REFERENCES [dbo].[productos] ([id])
GO
ALTER TABLE [dbo].[pedido_detalles] CHECK CONSTRAINT [fk_detalle_producto]
GO
ALTER TABLE [dbo].[pedidos]  WITH CHECK ADD  CONSTRAINT [fk_pedido_cliente] FOREIGN KEY([cliente_id])
REFERENCES [dbo].[clientes] ([id])
GO
ALTER TABLE [dbo].[pedidos] CHECK CONSTRAINT [fk_pedido_cliente]
GO
ALTER TABLE [dbo].[transaction]  WITH CHECK ADD FOREIGN KEY([id_account])
REFERENCES [dbo].[account] ([id_account])
GO
ALTER TABLE [dbo].[pedidos]  WITH CHECK ADD CHECK  (([estado]='ENTREGADO' OR [estado]='LISTO' OR [estado]='PREPARANDO' OR [estado]='PAGO_CONFIRMADO' OR [estado]='STOCK_CONFIRMADO' OR [estado]='CANCELADO' OR [estado]='GENERADO'))
GO
/****** Object:  StoredProcedure [dbo].[sp_get_transactions_filtered_paginated]    Script Date: 24/06/2025 20:40:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_get_transactions_filtered_paginated]
    @startDate DATETIME,
    @endDate DATETIME,
    @idAccount INT,
    @type VARCHAR(50),
    @page INT = 1,
    @size INT = 10,
    @totalElements INT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @offset INT = (@page - 1) * @size;

  

    -- Datos paginados
    SELECT 
	id_transaction AS id_transaction,
	amount,
	type,
	fecha_creacion,
	id_account,
	current_amount,
	previous_amount
    FROM [transaction]
    WHERE convert(date,fecha_creacion) BETWEEN convert(date,@startDate) AND CONVERT(date, @endDate)
      AND id_account = @idAccount
      AND type = @type
    ORDER BY fecha_creacion DESC
    OFFSET @offset ROWS FETCH NEXT @size ROWS ONLY;

	  -- Total de registros filtrados
     SELECT @totalElements = COUNT(*)
    FROM [transaction]
     WHERE convert(date,fecha_creacion) BETWEEN convert(date,@startDate) AND CONVERT(date, @endDate)
      AND id_account = @idAccount
      AND type = @type;
END
GO
USE [master]
GO
ALTER DATABASE [db_account] SET  READ_WRITE 
GO
