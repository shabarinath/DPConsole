-- --------------------------------------------------------
-- Host:                         184.168.47.19
-- Server version:               Microsoft SQL Server 2012 (SP4-GDR) (KB4057116) - 11.0.7462.6
-- Server OS:                    Windows NT 6.1 <X64> (Build 7601: Service Pack 1)
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES  */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for FFU
CREATE DATABASE IF NOT EXISTS "FFU";
USE "FFU";

-- Dumping structure for table FFU.Aliasing
CREATE TABLE IF NOT EXISTS "Aliasing" (
	"Swiggy" VARCHAR(max) NULL DEFAULT NULL,
	"FoodPanda" VARCHAR(max) NULL DEFAULT NULL,
	"UberEats" VARCHAR(max) NULL DEFAULT NULL,
	"Zomato" VARCHAR(max) NULL DEFAULT NULL,
	"ZomatoBVP" VARCHAR(max) NULL DEFAULT NULL
);

-- Data exporting was unselected.
-- Dumping structure for table FFU.DeliveryPartners
CREATE TABLE IF NOT EXISTS "DeliveryPartners" (
	"DPID" INT(10,0) NOT NULL,
	"DPName" VARCHAR(64) NULL DEFAULT NULL,
	"isActive" BIT NULL DEFAULT ((1)),
	PRIMARY KEY ("DPID"),
	UNIQUE KEY ("DPName")
);

-- Data exporting was unselected.
-- Dumping structure for table FFU.Departments
CREATE TABLE IF NOT EXISTS "Departments" (
	"DID" INT(10,0) NOT NULL,
	"DName" VARCHAR(64) NOT NULL,
	PRIMARY KEY ("DID"),
	UNIQUE KEY ("DName")
);

-- Data exporting was unselected.
-- Dumping structure for table FFU.Discounts
CREATE TABLE IF NOT EXISTS "Discounts" (
	"KID" INT(10,0) NOT NULL,
	"DPID" INT(10,0) NOT NULL,
	"FromDT" DATETIME(3) NOT NULL,
	"ToDT" DATETIME(3) NOT NULL,
	"Discount" INT(10,0) NULL DEFAULT NULL,
	PRIMARY KEY ("KID"),
	UNIQUE KEY ("FromDT"),
	UNIQUE KEY ("ToDT"),
	PRIMARY KEY ("DPID","FromDT","ToDT")
);

-- Data exporting was unselected.
-- Dumping structure for table FFU.ExpenseCategories
CREATE TABLE IF NOT EXISTS "ExpenseCategories" (
	"ECID" INT(10,0) NOT NULL,
	"ECName" VARCHAR(100) NULL DEFAULT NULL,
	"ECCode" VARCHAR(10) NULL DEFAULT NULL,
	"isActive" BIT NULL DEFAULT ((1)),
	PRIMARY KEY ("ECID")
);

-- Data exporting was unselected.
-- Dumping structure for table FFU.Expenses
CREATE TABLE IF NOT EXISTS "Expenses" (
	"EID" INT(10,0) NOT NULL,
	"ECID" INT(10,0) NULL DEFAULT NULL,
	"ExpName" VARCHAR(100) NULL DEFAULT NULL,
	"ExpAmount" MONEY(19,4) NULL DEFAULT NULL,
	"ExpOnDT" DATETIME(3) NULL DEFAULT NULL,
	"Notes" VARCHAR(1000) NULL DEFAULT NULL,
	"CrDT" DATETIME(3) NULL DEFAULT NULL,
	PRIMARY KEY ("EID")
);

-- Data exporting was unselected.
-- Dumping structure for table FFU.FoodType
CREATE TABLE IF NOT EXISTS "FoodType" (
	"FTID" INT(10,0) NOT NULL,
	"FTName" VARCHAR(64) NOT NULL,
	PRIMARY KEY ("FTID"),
	UNIQUE KEY ("FTName")
);

-- Data exporting was unselected.
-- Dumping structure for function FFU.GetIST
DELIMITER //
CREATE FUNCTION [dbo].[GetIST]()
RETURNS DATETIME
AS
BEGIN
    RETURN SWITCHOFFSET(SYSDATETIMEOFFSET(), '+05:30');
END
//
DELIMITER ;

-- Dumping structure for function FFU.GetISTFormat
DELIMITER //
CREATE FUNCTION [dbo].[GetISTFormat](@DT DATETIME)
RETURNS VARCHAR(20)
AS
BEGIN
	RETURN FORMAT(@DT, 'dd MMM yyyy hh:mm tt', 'en-IN');
END
//
DELIMITER ;

-- Dumping structure for function FFU.GetLocalDate
DELIMITER //
CREATE FUNCTION [dbo].[GetLocalDate]()
RETURNS DATETIME
AS 
BEGIN 
   RETURN CAST(SWITCHOFFSET(SYSDATETIMEOFFSET(), '+05:30') AS DATETIME)
END
//
DELIMITER ;

-- Dumping structure for table FFU.KitchenOrderData
CREATE TABLE IF NOT EXISTS "KitchenOrderData" (
	"KODID" INT(10,0) NOT NULL,
	"KOID" INT(10,0) NULL DEFAULT NULL,
	"MID" INT(10,0) NULL DEFAULT NULL,
	"Qty" INT(10,0) NULL DEFAULT NULL,
	"Total" MONEY(19,4) NOT NULL,
	PRIMARY KEY ("KODID")
);

-- Data exporting was unselected.
-- Dumping structure for table FFU.KitchenOrders
CREATE TABLE IF NOT EXISTS "KitchenOrders" (
	"KOID" INT(10,0) NOT NULL,
	"KID" INT(10,0) NULL DEFAULT NULL,
	"DPID" INT(10,0) NULL DEFAULT NULL,
	"KoTime" DATETIME(3) NULL DEFAULT NULL,
	"OSID" INT(10,0) NULL DEFAULT ((1)),
	"OrderRef" VARCHAR(64) NULL DEFAULT NULL,
	"Discount" INT(10,0) NULL DEFAULT ((0)),
	"Notes" VARCHAR(max) NULL DEFAULT NULL,
	"DP_Paid" MONEY(19,4) NULL DEFAULT NULL,
	"Flag" CHAR(2) NULL DEFAULT NULL,
	"PayType" VARCHAR(64) NULL DEFAULT NULL,
	"CrDT" DATETIME(3) NULL DEFAULT NULL,
	"IsPOP" BIT NULL DEFAULT NULL,
	PRIMARY KEY ("KOID")
);

-- Data exporting was unselected.
-- Dumping structure for table FFU.Kitchens
CREATE TABLE IF NOT EXISTS "Kitchens" (
	"KID" INT(10,0) NOT NULL,
	"KName" VARCHAR(64) NULL DEFAULT NULL,
	"isActive" BIT NULL DEFAULT ((1)),
	PRIMARY KEY ("KID"),
	UNIQUE KEY ("KName")
);

-- Data exporting was unselected.
-- Dumping structure for table FFU.Menu
CREATE TABLE IF NOT EXISTS "Menu" (
	"MID" INT(10,0) NOT NULL,
	"MCID" INT(10,0) NULL DEFAULT NULL,
	"FTID" INT(10,0) NULL DEFAULT NULL,
	"MName" VARCHAR(64) NOT NULL,
	"MOrder" INT(10,0) NOT NULL,
	"HalfPrice" SMALLMONEY(10,4) NULL DEFAULT NULL,
	"FullPrice" SMALLMONEY(10,4) NULL DEFAULT NULL,
	"RegularPrice" SMALLMONEY(10,4) NULL DEFAULT NULL,
	"OldRegularPrice" SMALLMONEY(10,4) NULL DEFAULT NULL,
	"MDescription" VARCHAR(256) NULL DEFAULT NULL,
	"isActive" BIT NULL DEFAULT ((1)),
	"SwiggyProposedPrice" SMALLMONEY(10,4) NULL DEFAULT NULL,
	"DID" INT(10,0) NULL DEFAULT ((1)),
	PRIMARY KEY ("MID"),
	UNIQUE KEY ("MName")
);

-- Data exporting was unselected.
-- Dumping structure for table FFU.MenuCategories
CREATE TABLE IF NOT EXISTS "MenuCategories" (
	"MCID" INT(10,0) NOT NULL,
	"MCName" VARCHAR(64) NOT NULL,
	"MCOrder" INT(10,0) NULL DEFAULT NULL,
	"isActive" BIT NULL DEFAULT ((1)),
	PRIMARY KEY ("MCID"),
	UNIQUE KEY ("MCOrder"),
	UNIQUE KEY ("MCName")
);

-- Data exporting was unselected.
-- Dumping structure for table FFU.Orders
CREATE TABLE IF NOT EXISTS "Orders" (
	"OID" INT(10,0) NOT NULL,
	"OMenu" VARCHAR(max) NOT NULL,
	"OTotal" SMALLMONEY(10,4) NOT NULL,
	"PayingBy" VARCHAR(64) NOT NULL,
	"NeedChangeFor" VARCHAR(8) NULL DEFAULT NULL,
	"ONotes" VARCHAR(564) NOT NULL,
	"OSID" INT(10,0) NULL DEFAULT NULL,
	"CxName" VARCHAR(64) NOT NULL,
	"CxMobile" VARCHAR(10) NOT NULL,
	"DeliveryLocation" VARCHAR(256) NOT NULL,
	"DeliveryTime" VARCHAR(64) NOT NULL,
	"CrDT" DATETIME(3) NOT NULL,
	"UpDT" DATETIME(3) NULL DEFAULT NULL,
	PRIMARY KEY ("OID")
);

-- Data exporting was unselected.
-- Dumping structure for table FFU.OrderSources
CREATE TABLE IF NOT EXISTS "OrderSources" (
	"OSID" INT(10,0) NOT NULL,
	"OSName" VARCHAR(64) NULL DEFAULT NULL,
	"isActive" BIT NULL DEFAULT ((1)),
	PRIMARY KEY ("OSID"),
	UNIQUE KEY ("OSName")
);

-- Data exporting was unselected.
-- Dumping structure for table FFU.OrderStatus
CREATE TABLE IF NOT EXISTS "OrderStatus" (
	"OSID" INT(10,0) NOT NULL,
	"OSName" VARCHAR(64) NOT NULL,
	PRIMARY KEY ("OSID"),
	UNIQUE KEY ("OSName")
);

-- Data exporting was unselected.
-- Dumping structure for table FFU.RestaurantMenu
CREATE TABLE IF NOT EXISTS "RestaurantMenu" (
	"RMID" INT(10,0) NOT NULL,
	"RMCID" INT(10,0) NULL DEFAULT NULL,
	"RID" INT(10,0) NULL DEFAULT NULL,
	"FTID" INT(10,0) NULL DEFAULT NULL,
	"RMName" VARCHAR(64) NULL DEFAULT NULL,
	"Cost" SMALLMONEY(10,4) NULL DEFAULT NULL,
	"PreparationCost" SMALLMONEY(10,4) NULL DEFAULT NULL,
	"DID" INT(10,0) NULL DEFAULT NULL,
	PRIMARY KEY ("RMID")
);

-- Data exporting was unselected.
-- Dumping structure for table FFU.RestaurantMenuCategories
CREATE TABLE IF NOT EXISTS "RestaurantMenuCategories" (
	"RMCID" INT(10,0) NOT NULL,
	"RID" INT(10,0) NULL DEFAULT NULL,
	"RMCName" VARCHAR(64) NULL DEFAULT NULL,
	PRIMARY KEY ("RMCID")
);

-- Data exporting was unselected.
-- Dumping structure for table FFU.RestaurantOrderData
CREATE TABLE IF NOT EXISTS "RestaurantOrderData" (
	"RODID" INT(10,0) NOT NULL,
	"ROID" INT(10,0) NULL DEFAULT NULL,
	"RMID" INT(10,0) NULL DEFAULT NULL,
	"Qty" INT(10,0) NULL DEFAULT NULL,
	"Total" SMALLMONEY(10,4) NULL DEFAULT NULL,
	PRIMARY KEY ("RODID")
);

-- Data exporting was unselected.
-- Dumping structure for table FFU.RestaurantOrders
CREATE TABLE IF NOT EXISTS "RestaurantOrders" (
	"ROID" INT(10,0) NOT NULL,
	"RID" INT(10,0) NULL DEFAULT NULL,
	"ROTID" INT(10,0) NULL DEFAULT NULL,
	"SaleDT" DATETIME(3) NULL DEFAULT NULL,
	"Notes" VARCHAR(564) NULL DEFAULT NULL,
	PRIMARY KEY ("ROID")
);

-- Data exporting was unselected.
-- Dumping structure for table FFU.RestaurantOrderTypes
CREATE TABLE IF NOT EXISTS "RestaurantOrderTypes" (
	"ROTID" INT(10,0) NOT NULL,
	"ROTName" VARCHAR(64) NULL DEFAULT NULL,
	PRIMARY KEY ("ROTID"),
	UNIQUE KEY ("ROTName")
);

-- Data exporting was unselected.
-- Dumping structure for table FFU.Restaurants
CREATE TABLE IF NOT EXISTS "Restaurants" (
	"RID" INT(10,0) NOT NULL,
	"RName" VARCHAR(64) NULL DEFAULT NULL,
	"IsActive" BIT NOT NULL,
	PRIMARY KEY ("RID"),
	UNIQUE KEY ("RName")
);

-- Data exporting was unselected.
-- Dumping structure for table FFU.Settings
CREATE TABLE IF NOT EXISTS "Settings" (
	"DPID" INT(10,0) NOT NULL,
	"SName" VARCHAR(64) NOT NULL,
	"SDesc" VARCHAR(64) NULL DEFAULT NULL,
	"SValue" VARCHAR(64) NOT NULL,
	PRIMARY KEY ("DPID","SName")
);

-- Data exporting was unselected.
-- Dumping structure for table FFU.StockData
CREATE TABLE IF NOT EXISTS "StockData" (
	"SDID" INT(10,0) NOT NULL,
	"SIID" INT(10,0) NULL DEFAULT NULL,
	"Consumed" INT(10,0) NULL DEFAULT ((0)),
	"Amount" SMALLMONEY(10,4) NOT NULL,
	"WorkShift" INT(10,0) NOT NULL,
	"ConsumedDT" DATETIME(3) NULL DEFAULT NULL,
	PRIMARY KEY ("SDID")
);

-- Data exporting was unselected.
-- Dumping structure for table FFU.StockInventoryData
CREATE TABLE IF NOT EXISTS "StockInventoryData" (
	"SIDID" INT(10,0) NOT NULL,
	"SIID" INT(10,0) NULL DEFAULT NULL,
	"Price" SMALLMONEY(10,4) NULL DEFAULT NULL,
	"Purchased" INT(10,0) NULL DEFAULT ((0)),
	"PurchasedDT" DATETIME(3) NULL DEFAULT NULL,
	PRIMARY KEY ("SIDID")
);

-- Data exporting was unselected.
-- Dumping structure for table FFU.StockItems
CREATE TABLE IF NOT EXISTS "StockItems" (
	"SIID" INT(10,0) NOT NULL,
	"SMCID" INT(10,0) NULL DEFAULT NULL,
	"SIName" VARCHAR(64) NOT NULL,
	"Unit" CHAR(2) NULL DEFAULT NULL,
	"SIOrder" INT(10,0) NULL DEFAULT NULL,
	PRIMARY KEY ("SIID"),
	UNIQUE KEY ("SIName")
);

-- Data exporting was unselected.
-- Dumping structure for table FFU.StockMainCategories
CREATE TABLE IF NOT EXISTS "StockMainCategories" (
	"SMCID" INT(10,0) NOT NULL,
	"SMCName" VARCHAR(64) NOT NULL,
	"Flag" CHAR(2) NULL DEFAULT ('01'),
	"SMCOrder" INT(10,0) NULL DEFAULT NULL,
	PRIMARY KEY ("SMCID"),
	UNIQUE KEY ("SMCName")
);

-- Data exporting was unselected.
-- Dumping structure for table FFU.UsersFFU
CREATE TABLE IF NOT EXISTS "UsersFFU" (
	"UFID" INT(10,0) NOT NULL,
	"UserName" VARCHAR(16) NOT NULL,
	"Pwd" NVARCHAR(64) NOT NULL,
	"UFTID" INT(10,0) NULL DEFAULT NULL,
	"CrDT" DATETIME(3) NULL DEFAULT NULL,
	"UpDT" DATETIME(3) NULL DEFAULT NULL,
	PRIMARY KEY ("UFID"),
	UNIQUE KEY ("UserName")
);

-- Data exporting was unselected.
-- Dumping structure for table FFU.UsersFFU_Types
CREATE TABLE IF NOT EXISTS "UsersFFU_Types" (
	"UFTID" INT(10,0) NOT NULL,
	"UFTName" VARCHAR(16) NOT NULL,
	PRIMARY KEY ("UFTID"),
	UNIQUE KEY ("UFTName")
);

-- Data exporting was unselected.
-- Dumping structure for procedure FFU.uspA_ChkLogin
DELIMITER //
CREATE PROCEDURE [dbo].[uspA_ChkLogin]
@UserName			VARCHAR(16),
@Password			NVARCHAR(64),
@UFTID				INT = NULL OUT,
@LoginStatus		BIT = NULL OUT
AS
BEGIN
	DECLARE @UFID INT = 0;
	SELECT @UFID = UFID, @UFTID = UFTID FROM UsersFFU WHERE UserName = @UserName AND Pwd = @Password
	IF(@UFID = 0) SET @LoginStatus = 0
	ELSE SET @LoginStatus = 1	
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_CreateDiscount
DELIMITER //
CREATE PROCEDURE [dbo].[uspA_CreateDiscount]
@TransStatus	INT = NULL OUT,
@KID			INT,
@DPID			INT,
@FromDT			DATETIME,
@ToDT			DATETIME,
@Discount		INT
AS
BEGIN	
	INSERT INTO Discounts (KID, DPID, FromDT, ToDT, Discount) 
	VALUES (@KID, @DPID, @FromDT, @ToDT, @Discount);
	--
	IF (@@ERROR = 0 AND @@ROWCOUNT = 1)	SET @TransStatus = 1;
	ELSE	SET @TransStatus = 0;
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_CreateRestaurantOrder
DELIMITER //
CREATE PROCEDURE [dbo].[uspA_CreateRestaurantOrder]
@RID			INT,
@ROTID			INT,
--@SaleDT			DATETIME,
@Notes			VARCHAR(564),
@ROID			INT = NULL OUT,
@TransStatus	INT = NULL OUT
AS
BEGIN	
	DECLARE @TmpRestaurantOrder TABLE (ROID INT);
	INSERT INTO RestaurantOrders (RID, ROTID, SaleDT, Notes) OUTPUT inserted.ROID INTO @TmpRestaurantOrder
	VALUES (@RID, @ROTID, dbo.GetIST(), @Notes);
	SET @ROID = (SELECT ROID FROM @TmpRestaurantOrder);	
	IF (@@ERROR = 0 AND @@ROWCOUNT = 1)	SET @TransStatus = 1
	ELSE	SET @TransStatus = 0
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_CreateRestaurantOrderData
DELIMITER //
CREATE PROCEDURE dbo.uspA_CreateRestaurantOrderData
@RODID			INT = NULL OUT,
@TransStatus	INT = NULL OUT,
@ROID			INT,
@RMID			INT,
@Qty			INT
AS
BEGIN
	DECLARE @TmpRestaurantOrderData TABLE (RODID INT);
	DECLARE @Total SMALLMONEY;
	SET @Total = (SELECT @Qty * Cost FROM RestaurantMenu WHERE RMID = @RMID);
	INSERT INTO RestaurantOrderData (ROID, RMID, Qty, Total) OUTPUT inserted.RODID INTO @TmpRestaurantOrderData
	VALUES (@ROID, @RMID, @Qty, @Total);
	SET @RODID = (SELECT RODID FROM @TmpRestaurantOrderData);
	IF (@@ERROR = 0 AND @@ROWCOUNT = 1)	SET @TransStatus = 1
	ELSE	SET @TransStatus = 0
END//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_CreateStockData
DELIMITER //
--DROP PROCEDURE dbo.uspA_UpdStockItemPurchased
--@TransStatus		INT = NULL OUT,
--@SIID				INT,
--@Purchased			INT
--AS
--BEGIN
--	UPDATE StockItems SET Purchased = @Purchased WHERE SIID = @SIID;
--	IF (@@ERROR = 0 AND @@ROWCOUNT = 1)	SET @TransStatus = 1
--	ELSE	SET @TransStatus = 0
--END
--GO
CREATE PROCEDURE [dbo].[uspA_CreateStockData]
@TransStatus			INT = NULL OUT,
@SDID					INT = NULL OUT,
@SIID					INT,
@Consumed				INT,
--@Amount				SMALLMONEY,
@WorkShift				INT,
@ConsumedDT				DATETIME
AS
BEGIN
	DECLARE @TmpStockData TABLE (SDID INT);
	DECLARE @Amount	SMALLMONEY;
	DECLARE @Unit CHAR(2);
	DECLARE @RecentPrice SMALLMONEY;
	SET @Unit = (SELECT Unit FROM StockItems WHERE SIID = @SIID);
	SET @RecentPrice = (SELECT TOP 1 Price FROM StockInventoryData WHERE SIID = @SIID ORDER BY SIDID DESC)
	--if unit is g Consumed/1000  * RecentPrice

	IF(@Unit = 'G')
	BEGIN
		SET @Amount = (@RecentPrice/1000) * @Consumed;
	END
	ELSE
	BEGIN	
		SET @Amount = @Consumed * @RecentPrice;
	END

	INSERT INTO StockData (SIID, Consumed, Amount, WorkShift, ConsumedDT) OUTPUT inserted.SDID INTO @TmpStockData
	VALUES (@SIID, @Consumed, @Amount, @WorkShift, @ConsumedDT);
	--
	SET @SDID = (SELECT SDID FROM @TmpStockData);
	IF (@@ERROR = 0 AND @@ROWCOUNT = 1)	SET @TransStatus = 1
	ELSE	SET @TransStatus = 0
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_GetDayMatrix
DELIMITER //
--EXEC uspA_GetDayMatrix '2018-02-23 00:00:00', '2018-10-25 23:59:59'
CREATE PROCEDURE [dbo].[uspA_GetDayMatrix]
@FromDT			DATETIME,
@ToDT			DATETIME
AS
BEGIN
DECLARE @TmpOrdersTBL TABLE(KID INT, DPID INT, KName VARCHAR(64), DPName VARCHAR(64), OrdersCount INT, TotalCount INT)
INSERT INTO @TmpOrdersTBL
SELECT K.KID, DP.DPID, K.KName, DP.DPName,
(SELECT COUNT(KO2.KOID) FROM KitchenOrders KO2 WHERE KO2.KID = K.KID AND KO2.DPID = DP.DPID AND KO2.KoTime BETWEEN @FromDT AND @ToDT) [OrdersCount],
(SELECT SUM(KOD.Qty) FROM KitchenOrderData KOD 
INNER JOIN KitchenOrders KO3 ON KO3.KOID = KOD.KOID 
 WHERE KO3.KID = K.KID AND KO3.DPID = DP.DPID AND KO3.KoTime BETWEEN @FromDT AND @ToDT) [TotalCount]

FROM KitchenOrders KO 
CROSS JOIN Kitchens K 
CROSS JOIN DeliveryPartners DP 
GROUP BY K.KID, DP.DPID, K.KName, DP.DPName

SELECT O.KID, O.DPID, O.KName, O.DPName, 
(SELECT --SUM(KOD.Qty * M.RegularPrice) 
SUM(Total)
FROM KitchenOrderData KOD 
INNER JOIN KitchenOrders KO ON KO.KOID = KOD.KOID 
INNER JOIN Menu M ON M.MID = KOD.MID WHERE KO.KID = O.KID AND KO.DPID = O.DPID AND KO.KoTime BETWEEN @FromDT AND @ToDT 
GROUP BY KO.KID, KO.DPID) [Total], O.OrdersCount, O.TotalCount
FROM @TmpOrdersTBL O
ORDER BY O.KID, O.DPID
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_GetDeliveredCards
DELIMITER //

CREATE PROCEDURE [dbo].[uspA_GetDeliveredCards]
@DPID			INT
AS
BEGIN
	SET NOCOUNT ON;
	SELECT TOP 100 KO.KOID, K.KName, KO.OrderRef, KO.Notes, dbo.GetISTFormat(KO.KoTime) [KoTimeStr]
	FROM KitchenOrders KO 
	LEFT JOIN Kitchens K ON KO.KID = K.KID
	WHERE Flag = '03' AND DPID = @DPID
	ORDER BY KoTime DESC;
	--
	SELECT KOD.KOID, M.MName, KOD.Qty
	FROM KitchenOrderData KOD 
	LEFT JOIN Menu M ON KOD.MID = M.MID
	WHERE KOID IN (SELECT TOP 100 KOID FROM KitchenOrders WHERE Flag = '03' AND DPID = @DPID ORDER BY KoTime DESC);
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_GetDeliveryPartnersDDL
DELIMITER //
CREATE PROCEDURE [dbo].[uspA_GetDeliveryPartnersDDL]
AS
BEGIN
	SET NOCOUNT ON;
	SELECT DPID,DPName FROM DeliveryPartners WHERE isActive = 1
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_GetDiscountAndShare
DELIMITER //
--SELECT * FROM KitchenOrders
--SELECT * FROM KitchenOrderData
--SELECT * FROM Discounts
--DELETE FROM Discounts
--INSERT INTO Discounts VALUES (1,3,'2018-02-01','2018-02-28',10)
--UPDATE Discounts SET Discount = 10 WHERE KID = 1 AND DPID = 3
--SELECT * FROM Settings
--UPDATE Settings SET SValue = 20 WHERE SID = 3
--EXEC uspA_GetDiscountAndShare '2018-03-19 00:00:00',1,4,1616
CREATE PROCEDURE [dbo].[uspA_GetDiscountAndShare]
@KoTime		DATETIME,
@KID		INT,
@DPID		INT,
@MID		INT
AS
BEGIN
	DECLARE @Discount MONEY, @Share MONEY, @SwiggyProposedPrice SMALLMONEY;
	DECLARE @RegularPrice MONEY;
	DECLARE @DPName VARCHAR(64);	
	DECLARE @DCount INT = 0;
	SET @DPName = (SELECT DPName FROM DeliveryPartners WHERE DPID = @DPID);
	--
	SET @Share = (SELECT CAST(SValue AS MONEY) FROM Settings WHERE DPID = @DPID AND SName = 'SHARE');
	SET @DCount = (SELECT COUNT(Discount) FROM Discounts WHERE @KoTime BETWEEN FromDT AND ToDT AND KID = @KID AND DPID = @DPID);	
	SET @RegularPrice = (SELECT RegularPrice FROM Menu WHERE MID = @MID);
	IF(@DCount = 0)
	BEGIN
		SET @Discount = (SELECT CAST(SValue AS INT) FROM Settings WHERE DPID = @DPID AND SName = 'Default_DISCOUNT');
	END
	ELSE
	BEGIN
		SET @Discount = (SELECT Discount FROM Discounts WHERE @KoTime BETWEEN FromDT AND ToDT AND KID = @KID AND DPID = @DPID);
	END
	SET @SwiggyProposedPrice = (SELECT SwiggyProposedPrice FROM Menu WHERE MID = @MID);
	SELECT @Discount [Discount], @Share[Share], @RegularPrice [RegularPrice], @SwiggyProposedPrice [SwiggyProposedPrice];
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_GetExpenseCategoriesDDL
DELIMITER //
CREATE PROCEDURE [dbo].[uspA_GetExpenseCategoriesDDL]
AS
BEGIN
	SET NOCOUNT ON;
	SELECT ECID,ECName FROM ExpenseCategories WHERE isActive = 1
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_GetExpenses
DELIMITER //
--EXEC uspA_GetExpenses NULL,10,0,NULL, NULL,NULL, '2018-01-01', '2018-01-31',NULL
CREATE PROCEDURE [dbo].[uspA_GetExpenses]
@Total				MONEY = NULL OUT,
@DisplayLength		INT,
@DisplayStart		INT,
@SortCol			INT = NULL,
@SortDir			NVARCHAR(10) = NULL,
@Search				NVARCHAR(255) = NULL,
@FromDT				DATETIME,
@ToDT				DATETIME,
@ECID				INT = NULL
AS
BEGIN
	SET NOCOUNT ON;			
	SET @Total = (SELECT SUM(E.ExpAmount) FROM Expenses E
	INNER JOIN ExpenseCategories EC ON E.ECID = EC.ECID 
	WHERE (@ECID IS NULL OR EC.ECID = @ECID) AND (E.ExpOnDT BETWEEN @FromDT AND @ToDT));	
	--
	DECLARE @FirstRec INT = @DisplayStart, @LastRec INT = @DisplayStart + @DisplayLength;	
	WITH CTE AS
	(
        SELECT ROW_NUMBER() OVER (ORDER BY EC.ECName) AS RowNum, COUNT(*) OVER() AS TotalCount,
		EC.ECName,SUM(E.ExpAmount) [GrossTotal] FROM Expenses E 
		INNER JOIN ExpenseCategories EC ON E.ECID = EC.ECID		
		WHERE (@ECID IS NULL OR EC.ECID = @ECID) AND (E.ExpOnDT BETWEEN @FromDT AND @ToDT)		
		GROUP BY EC.ECName		
    )
    SELECT * FROM CTE 
	WHERE RowNum > @FirstRec AND RowNum <= @LastRec;
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_GetFoodTypesDDL
DELIMITER //
CREATE PROCEDURE [dbo].[uspA_GetFoodTypesDDL]
AS
BEGIN
	SET NOCOUNT ON;
	SELECT FTID, FTName FROM FoodType
	ORDER BY FTID 
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_GetKitchenOrderData
DELIMITER //
--EXEC uspA_GetKitchenOrderData 1564
CREATE PROCEDURE [dbo].[uspA_GetKitchenOrderData]
@KOID			INT
AS
BEGIN
	SELECT M.MName, KOD.Qty, KOD.Total
	FROM KitchenOrderData KOD 
	INNER JOIN Menu M ON M.MID = KOD.MID
	WHERE KOD.KOID = @KOID
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_GetKitchenOrders
DELIMITER //
--EXEC uspA_GetKitchenOrders 100,0,NULL,NULL,  NULL,  NULL, NULL,'2018-04-30 00:00','2018-04-30 23:59'
CREATE PROCEDURE [dbo].[uspA_GetKitchenOrders]
@DisplayLength		INT,
@DisplayStart		INT,
@SortCol			INT = NULL,
@SortDir			NVARCHAR(10) = NULL,
@Search				NVARCHAR(255) = NULL,
@KID				INT = NULL,
@DPID				INT = NULL,
@FromDT				DATETIME = NULL,
@ToDT				DATETIME = NULL
AS
BEGIN
	SET NOCOUNT ON;	
	--SET @DisplayLength = 99999999;
	DECLARE @FirstRec INT = @DisplayStart, @LastRec INT = @DisplayStart + @DisplayLength;
	WITH CTE AS
	(
        SELECT ROW_NUMBER() OVER (ORDER BY KO.KOID DESC) AS RowNum,
		COUNT(*) OVER() AS TotalCount,
		KO.KOID,KO.OrderRef, K.KName, DP.DPName, CONVERT(VARCHAR(17), KO.KoTime, 113) [KoTimeStr], DP_Paid,
		--SUM(KOD.Total) [Total]
		(SELECT SUM(Total) FROM KitchenOrderData WHERE KOID = KO.KOID) [Total]
		FROM KitchenOrders KO
		INNER JOIN Kitchens K ON K.KID = KO.KID
		INNER JOIN DeliveryPartners DP ON DP.DPID = KO.DPID		
		WHERE (@KID IS NULL OR KO.KID = @KID)
			AND (@DPID IS NULL OR KO.DPID = @DPID)			
			AND (KO.KoTime BETWEEN @FromDT AND @ToDT)
			AND (@Search IS NULL OR KO.OrderRef LIKE '%'+@Search+'%')				
		--GROUP BY KO.KOID,KO.OrderRef, K.KName, DP.DPName, KO.KoTime
    )
    SELECT * FROM CTE 
	WHERE RowNum > @FirstRec AND RowNum <= @LastRec
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_GetKitchensDDL
DELIMITER //
CREATE PROCEDURE [dbo].[uspA_GetKitchensDDL]
AS
BEGIN
	SET NOCOUNT ON;
	SELECT KID, KName FROM Kitchens WHERE isActive = 1
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_GetMenu
DELIMITER //
CREATE PROCEDURE [dbo].[uspA_GetMenu]
AS
BEGIN
		SELECT MC.MCID, MC.MCName, 
		M.MID, M.MName, M.HalfPrice, M.FullPrice, M.RegularPrice, M.OldRegularPrice, M.FTID, M.MDescription
		FROM Menu M INNER JOIN MenuCategories MC ON MC.MCID = M.MCID
		WHERE M.isActive = 1
		ORDER BY M.FTID, M.MOrder
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_GetMenuByMID
DELIMITER //
CREATE PROCEDURE [dbo].[uspA_GetMenuByMID]
@MID					INT
AS
BEGIN
	SELECT MID, MName, MCID, FTID, MOrder, RegularPrice, SwiggyProposedPrice, isActive
	FROM Menu 
	WHERE MID = @MID
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_GetMenuCategoriesDDL
DELIMITER //
CREATE PROCEDURE [dbo].[uspA_GetMenuCategoriesDDL]
AS
BEGIN
	SET NOCOUNT ON;
	SELECT MCID, MCName FROM MenuCategories
	WHERE isActive = 1
	ORDER BY MCOrder
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_GetMenuItemsDDL
DELIMITER //
CREATE PROCEDURE [dbo].[uspA_GetMenuItemsDDL]
@MCID			INT
AS
BEGIN
	SET NOCOUNT ON;
	SELECT MID, MName FROM Menu 
	WHERE MCID = @MCID AND isActive = 1
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_GetMenuMgmt
DELIMITER //
CREATE PROCEDURE [dbo].[uspA_GetMenuMgmt]
@DisplayLength		INT,
@DisplayStart		INT,
@SortCol			INT = NULL,
@SortDir			NVARCHAR(10) = NULL,
@Search				NVARCHAR(255) = NULL,
@MCID				INT
AS
BEGIN
	SET NOCOUNT ON;
	DECLARE @FirstRec INT = @DisplayStart, @LastRec INT = @DisplayStart + @DisplayLength;
	WITH CTE AS
	(
        SELECT ROW_NUMBER() OVER (ORDER BY M.MID) AS RowNum, COUNT(*) OVER() AS TotalCount,
		M.MID, M.MName, MC.MCName, FT.FTName, M.MOrder, M.RegularPrice, M.isActive
		FROM Menu M
		INNER JOIN MenuCategories MC ON MC.MCID = M.MCID 
		INNER JOIN FoodType FT ON FT.FTID = M.FTID
		WHERE 
		MC.MCID = @MCID AND 
		(@Search IS NULL OR M.MName LIKE '%'+@Search+'%')
    )
    SELECT * FROM CTE
	WHERE RowNum > @FirstRec AND RowNum <= @LastRec;
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_GetMenuRPT
DELIMITER //
--uspA_GetMenuRPT '2018-03-01 00:00:00','2018-03-31 23:59:00', NULL, NULL
CREATE PROCEDURE [dbo].[uspA_GetMenuRPT]
@FromDT		DATETIME,
@ToDT		DATETIME,
@KID		INT = NULL,
@DPID		INT = NULL
AS
BEGIN
	SET NOCOUNT ON;
	SELECT MCID, MCName FROM MenuCategories ORDER BY MCOrder;
	SELECT 
		M.MCID, M.MName, SUM(KOD.Qty)[Qty], M.RegularPrice, SUM(KOD.Qty * M.RegularPrice) [NetAmount] 
	FROM Menu M 
	INNER JOIN KitchenOrderData KOD ON M.MID = KOD.MID
	INNER JOIN KitchenOrders KO ON KO.KOID = KOD.KOID	
	WHERE (@KID IS NULL OR KO.KID = @KID) AND 
		(@DPID IS NULL OR KO.DPID = @DPID) AND 
		KO.KoTime BETWEEN @FromDT AND @ToDT
	GROUP BY M.MName, M.MCID, M.RegularPrice
	ORDER BY M.MName
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_GetMIDByMenuNameLike
DELIMITER //
--SELECT * FROM Menu
--EXEC uspA_GetMIDByMenuNameLike 'Apollo FISH', NULL
CREATE PROCEDURE [dbo].[uspA_GetMIDByMenuNameLike]
@MName			VARCHAR(64),
@MID			INT = NULL OUT
AS
BEGIN	
	SET @MID = 0;
	SELECT TOP 1 @MID = MID FROM Menu WHERE MName = @MName	
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_GetOrders
DELIMITER //
CREATE PROCEDURE [dbo].[uspA_GetOrders]
@DisplayLength				INT,
@DisplayStart				INT,
@SortCol					INT,
@SortDir					NVARCHAR(10),
@Search						NVARCHAR(255) = NULL,
@OSID						INT
AS
BEGIN
	SET NOCOUNT ON;	
	DECLARE @FirstRec INT = @DisplayStart, @LastRec INT = @DisplayStart + @DisplayLength;
	WITH CTE_NewOrders AS
	(
         SELECT ROW_NUMBER() OVER (ORDER BY 
			 CASE WHEN (@SortCol = 1 AND @SortDir = 'asc') THEN CrDT END ASC,
			 CASE WHEN (@SortCol = 1 AND @SortDir = 'desc') THEN CrDT END DESC		 
			 )	AS RowNum,
		COUNT(*) OVER() AS TotalCount,
		OID, OMenu, OTotal, PayingBy, NeedChangeFor, ONotes, OSID, CxName, CxMobile, DeliveryLocation, DeliveryTime, 
		CONVERT(VARCHAR(20), CrDT, 100) [CrDTStr], CONVERT(VARCHAR(20), UpDT, 100) [UpDTStr]
		FROM Orders O 
		WHERE (@Search IS NULL
				OR CxName LIKE '%' + @Search + '%' 
				OR CxMobile LIKE '%' + @Search + '%'
				OR DeliveryLocation LIKE '%' + @Search + '%' )
				AND OSID = @OSID
    )
    SELECT * FROM CTE_NewOrders WHERE RowNum > @FirstRec AND RowNum <= @LastRec
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_GetRestaurantMenu
DELIMITER //
CREATE PROCEDURE [dbo].[uspA_GetRestaurantMenu]
AS
BEGIN
		SELECT RM.RMID,	RMC.RMCID, RMC.RMCName, RM.RMName, RM.Cost
		FROM RestaurantMenu RM INNER JOIN RestaurantMenuCategories RMC ON RMC.RMCID = RM.RMCID		
		ORDER BY RM.RMName
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_GetRestaurantMenuCategoriesDDL
DELIMITER //
--uspA_GetRestaurantMenuCategoriesDDL 1
CREATE PROCEDURE [dbo].[uspA_GetRestaurantMenuCategoriesDDL]
@RID			INT
AS
BEGIN
	SET NOCOUNT ON;
	SELECT RMCID, RMCName FROM RestaurantMenuCategories
	WHERE RID = @RID
	ORDER BY RMCID
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_GetRTCards
DELIMITER //
--EXEC uspA_GetRTCards
CREATE PROCEDURE [dbo].[uspA_GetRTCards]
AS
BEGIN
	DECLARE @CurrentTime DATETIME = dbo.GetLocalDate();
	SET NOCOUNT ON;
	SELECT KO.KOID, KO.DPID, K.KName, KO.OrderRef, KO.Notes, dbo.GetISTFormat(KO.KoTime) [KoTimeStr], KO.Flag, KO.PayType
	FROM KitchenOrders KO 
	LEFT JOIN Kitchens K ON KO.KID = K.KID
	WHERE Flag IN ('01','02') AND DPID IN (1, 4)
	AND KoTime > DATEADD(HOUR, -1, @CurrentTime)
	ORDER BY KoTime;
	--
	SELECT KOD.KOID, M.MName, KOD.Qty, KOD.MID, M.MCID
	FROM KitchenOrderData KOD 
	LEFT JOIN Menu M ON KOD.MID = M.MID
	WHERE KOID IN (SELECT KOID FROM KitchenOrders 
	WHERE Flag IN ('01','02') AND DPID IN (1, 4)
	AND KoTime > DATEADD(HOUR, -1, @CurrentTime)
	);				
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_GetStatsTBL
DELIMITER //
CREATE PROCEDURE [dbo].[uspA_GetStatsTBL]
@DisplayLength		INT,
@DisplayStart		INT,
@SortCol			INT = NULL,
@SortDir			NVARCHAR(10) = NULL,
@Search				NVARCHAR(255) = NULL,
@DPID				INT = NULL,
@FromDT				DATETIME = NULL,
@ToDT				DATETIME = NULL,
@MCID				INT = NULL,
@MID				INT = NULL
AS
BEGIN
	SET NOCOUNT ON;	
	DECLARE @FirstRec INT = @DisplayStart, @LastRec INT = @DisplayStart + @DisplayLength;
	WITH CTE AS
	(
        SELECT ROW_NUMBER() OVER (ORDER BY KO.KOID) AS RowNum,
		COUNT(*) OVER() AS TotalCount,
		KO.KOID, KO.KoTime, MC.MCName, M.MName, KOD.Qty,M.RegularPrice, SUM(KOD.Qty * M.RegularPrice) [Amount]
		FROM KitchenOrders KO 
		INNER JOIN KitchenOrderData KOD ON KOD.KOID = KO.KOID 
		INNER JOIN DeliveryPartners DP ON DP.DPID = KO.DPID
		INNER JOIN Menu M ON M.MID = KOD.MID 
		INNER JOIN MenuCategories MC ON MC.MCID = M.MCID
		WHERE (@DPID IS NULL OR KO.DPID = @DPID)
			AND (@MCID IS NULL OR MC.MCID = @MCID)
			AND (@MID IS NULL OR M.MID = @MID)
			AND (KO.KoTime BETWEEN @FromDT AND @ToDT)
		GROUP BY KO.KOID, KO.KoTime, MC.MCName, M.MName, KOD.Qty, M.RegularPrice
    )
    SELECT * FROM CTE 
	WHERE RowNum > @FirstRec AND RowNum <= @LastRec
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_GetStockDataRPT
DELIMITER //
--EXEC uspA_GetStockDataRPT '2018-07-13 00:00:00','2018-07-14 23:59:00', NULL, NULL
CREATE PROCEDURE [dbo].[uspA_GetStockDataRPT]
@FromDT		DATETIME,
@ToDT		DATETIME,
@SMCID		INT = NULL,
@WorkShift	INT = NULL
AS
BEGIN
	SET NOCOUNT ON;
	SELECT SMCID, SMCName FROM StockMainCategories 
	WHERE (@SMCID IS NULL OR SMCID = @SMCID) 
	ORDER BY SMCName;
	SELECT 
		SMC.SMCID, SI.SIName, SD.Consumed, SD.Amount, CONVERT(VARCHAR(11), SD.ConsumedDT, 106) ConsumedDTStr,
		CASE SD.WorkShift WHEN 1 THEN 'Morning' ELSE 'Night' END [WorkShiftStr]
	FROM StockData SD
	INNER JOIN StockItems SI ON SI.SIID = SD.SIID	
	INNER JOIN StockMainCategories SMC ON SMC.SMCID = SI.SMCID
	WHERE (@SMCID IS NULL OR SMC.SMCID = @SMCID) AND 
		(@WorkShift IS NULL OR SD.WorkShift = @WorkShift) AND 
		SD.ConsumedDT BETWEEN @FromDT AND @ToDT		
	ORDER BY SD.ConsumedDT DESC
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_GetStockInvData
DELIMITER //
--EXEC uspA_GetStockData 100,0,0,'',NULL,'2018-07-13', '2018-07-14'
CREATE PROCEDURE [dbo].[uspA_GetStockInvData]
@DisplayLength				INT,
@DisplayStart				INT,
@SortCol					INT,
@SortDir					NVARCHAR(10),
@Search						NVARCHAR(255) = NULL,
@FromDT						DATETIME,
@ToDT						DATETIME
AS
BEGIN	
	SET NOCOUNT ON;	
	DECLARE @FirstRec INT = @DisplayStart, @LastRec INT = @DisplayStart + @DisplayLength;
	WITH CTE AS
	(
         SELECT ROW_NUMBER() OVER (ORDER BY SID.SIID)	AS RowNum,
		COUNT(*) OVER() AS TotalCount,
		SI.SIName, SID.Price, SID.Purchased, CONVERT(VARCHAR(11), SID.PurchasedDT, 106) PurchasedDTStr
		FROM StockInventoryData SID		
		INNER JOIN StockItems SI ON SI.SIID = SID.SIID
		WHERE (SID.PurchasedDT BETWEEN @FromDT AND @ToDT)				
    )
    SELECT * FROM CTE WHERE RowNum > @FirstRec AND RowNum <= @LastRec	
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_GetStockItems
DELIMITER //
CREATE PROCEDURE [dbo].[uspA_GetStockItems]
AS
BEGIN
	SET NOCOUNT ON;
	SELECT SMCID, SMCName 
	FROM StockMainCategories
	WHERE Flag = '01' 
	ORDER BY SMCOrder
	--
	SELECT SIID, SMCID, SIName, Unit
	FROM StockItems ORDER BY SIOrder
	--
	SELECT DISTINCT SID.SIID, (SELECT TOP 1 Price FROM StockInventoryData WHERE SIID = SID.SIID ORDER BY SIDID DESC) RecentPrice, 
	(SELECT SUM(Purchased) FROM StockInventoryData WHERE SIID = SID.SIID) TotalPurchased,  (SELECT SUM(Consumed) FROM StockData WHERE SIID = SID.SIID) TotalConsumed
	FROM StockInventoryData SID	
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_GetStockMainCategoriesDDL
DELIMITER //
CREATE PROCEDURE [dbo].[uspA_GetStockMainCategoriesDDL]
AS
BEGIN
	SET NOCOUNT ON;
	SELECT SMCID, SMCName FROM StockMainCategories WHERE Flag='01'
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_InsExpense
DELIMITER //
CREATE PROCEDURE [dbo].[uspA_InsExpense]
@TransStatus	INT = NULL OUT,
@EID	INT = NULL OUT,
@ECID	INT,
@ExpName	VARCHAR(100),
@ExpAmount	MONEY,
@ExpOnDT	DATETIME,
@Notes	VARCHAR(1000) = NULL
AS
BEGIN
	DECLARE @TblExpense TABLE (EID INT);
	INSERT INTO Expenses (ECID,ExpName,ExpAmount,ExpOnDT,Notes,CrDT) OUTPUT inserted.EID INTO @TblExpense
	VALUES(@ECID,@ExpName,@ExpAmount,@ExpOnDT,@Notes,dbo.GetLocalDate());
	SELECT @EID = EID FROM @TblExpense
	IF (@@ERROR = 0 AND @@ROWCOUNT = 1)	SET @TransStatus = 1
	ELSE	SET @TransStatus = 0
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_InsKitchenOrder
DELIMITER //
--SELECT * FROM KitchenOrders WHERE OrderRef= '#1148770650'
CREATE PROCEDURE [dbo].[uspA_InsKitchenOrder]
@KID			INT,
@DPID			INT,
@KoTime			DATETIME,
@OrderRef		VARCHAR(64) = NULL,
@PayType		VARCHAR(64) = NULL,
@Discount		INT,
@Notes			VARCHAR(MAX) = NULL,
@Flag			CHAR(02) = NULL,
@IsPOP			BIT = NULL,
@KOID			INT = NULL OUT,
@TransStatus	INT = NULL OUT
AS
BEGIN
	DECLARE @TblKitchenOrder TABLE (KOID INT);
	--CHECK IF ORDERREF already exists  as it's not unique
	DECLARE @OrderRefVal VARCHAR(64);
	SET @OrderRefVal = (SELECT OrderRef FROM KitchenOrders WHERE OrderRef = @OrderRef);
	IF(@OrderRefVal = @OrderRef)
	BEGIN		
		SET @KOID = 0;
		SET @TransStatus = 2;
	END
	ELSE
	BEGIN
		INSERT INTO KitchenOrders (KID, DPID, KoTime, OrderRef, Discount, NOTES, Flag, IsPOP, PayType, CrDT) OUTPUT inserted.KOID INTO @TblKitchenOrder
		VALUES(@KID, @DPID, @KoTime, @OrderRef, @Discount, @Notes, @Flag, @IsPOP, @PayType, dbo.GetIST());
		SELECT @KOID = KOID FROM @TblKitchenOrder	
		IF (@@ERROR = 0 AND @@ROWCOUNT = 1)	SET @TransStatus = 1
		ELSE SET @TransStatus = 0
	END
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_InsKitchenOrderData
DELIMITER //
CREATE PROCEDURE [dbo].[uspA_InsKitchenOrderData]
@KOID			INT,
@MID			INT,
@Qty			INT,
@Total			MONEY,
@KODID			INT = NULL OUT,
@TransStatus	INT = NULL OUT
AS
BEGIN	
	DECLARE @TblKitchenOrderData TABLE (KODID INT);	
	INSERT INTO KitchenOrderData (KOID, MID, Qty, Total) OUTPUT inserted.KODID INTO @TblKitchenOrderData
	VALUES(@KOID, @MID, @Qty, @Total);
	SELECT @KODID = KODID FROM @TblKitchenOrderData
	IF (@@ERROR = 0 AND @@ROWCOUNT = 1)	SET @TransStatus = 1
	ELSE	SET @TransStatus = 0
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_InsMenu
DELIMITER //
CREATE PROCEDURE [dbo].[uspA_InsMenu]
@TransStatus			INT = NULL OUT,
@MID					INT = NULL OUT,
@MCID					INT,
@FTID					INT,
@MName					VARCHAR(64),
@MOrder					INT,
@RegularPrice			SMALLMONEY,
@SwiggyProposedPrice	SMALLMONEY = NULL,
@isActive				BIT
AS
BEGIN
	DECLARE @TblMenu TABLE (MID INT)
	INSERT INTO Menu 
		(MCID, FTID, MName, MOrder, RegularPrice, SwiggyProposedPrice, isActive) OUTPUT inserted.MID INTO @TblMenu
	VALUES (@MCID, @FTID, @MName, @MOrder, @RegularPrice, @SwiggyProposedPrice, @isActive) 
	SELECT @MID = MID FROM @TblMenu;
	--
	IF (@@ERROR = 0 AND @@ROWCOUNT = 1)	SET @TransStatus = 1
	ELSE	SET @TransStatus = 0
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_InsOrUpdStockInvData
DELIMITER //
CREATE PROCEDURE [dbo].[uspA_InsOrUpdStockInvData]
@TransStatus			INT = NULL OUT,
@SIID					INT,
@Price					SMALLMONEY,
@Purchased				INT, 
@PurchasedDT			DATETIME
AS
BEGIN
	IF(@Purchased != 0)
	BEGIN
		INSERT INTO StockInventoryData (SIID, Price, Purchased, PurchasedDT) 
		VALUES (@SIID, @Price, @Purchased, @PurchasedDT)
	END
	ELSE 
	BEGIN
		DECLARE @SIDID INT;
		SET @SIDID = (SELECT TOP 1 SIDID FROM StockInventoryData WHERE SIID = @SIID ORdER BY SIDID DESC);
		UPDATE StockInventoryData SET Price = @Price WHERE SIDID = @SIDID;
	END	
	IF (@@ERROR = 0 AND @@ROWCOUNT = 1)	SET @TransStatus = 1
	ELSE	SET @TransStatus = 0
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_ReadDiscounts
DELIMITER //
--EXEC uspA_ReadDiscounts 100,0,1,NULL,NULL

--SELECT * FROM Discounts
CREATE PROCEDURE [dbo].[uspA_ReadDiscounts]
@DisplayLength		INT,
@DisplayStart		INT,
@SortCol			INT = NULL,
@SortDir			NVARCHAR(10) = NULL,
@Search				NVARCHAR(255) = NULL
AS
BEGIN
	SET NOCOUNT ON;
	DECLARE @FirstRec INT = @DisplayStart, @LastRec INT = @DisplayStart + @DisplayLength;	
	WITH CTE AS
	(
        SELECT ROW_NUMBER() OVER (ORDER BY D.ToDT DESC) AS RowNum, COUNT(*) OVER() AS TotalCount,
		D.KID, D.DPID, K.KName, DP.DPName, 
		CONVERT(VARCHAR(10),D.FromDT, 103)+ ' '+ CONVERT(VARCHAR(5),D.FromDT, 108) [FromDTStr], 
		CONVERT(VARCHAR(10),D.ToDT, 103)+ ' '+ CONVERT(VARCHAR(5),D.ToDT, 108) [ToDTStr], D.Discount
		FROM Discounts D
		INNER JOIN Kitchens K ON D.KID = K.KID  
		INNER JOIN DeliveryPartners DP ON D.DPID = DP.DPID 
    )
    SELECT * FROM CTE 
	WHERE RowNum > @FirstRec AND RowNum <= @LastRec;
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_ReadRestaurantMenu
DELIMITER //
CREATE PROCEDURE [dbo].[uspA_ReadRestaurantMenu]
@RMCID					INT
AS
BEGIN
	SET NOCOUNT ON;
	SELECT RMID, RMCID, RID, FTID, RMName, Cost, PreparationCost 
	FROM RestaurantMenu
	WHERE RMCID = @RMCID
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_ReadRestaurantMenuByRMID
DELIMITER //
CREATE PROCEDURE [dbo].[uspA_ReadRestaurantMenuByRMID]
@RMID					INT
AS
BEGIN
	SET NOCOUNT ON;
	SELECT RMID, RMCID, RID, FTID, RMName, Cost, PreparationCost
	FROM RestaurantMenu
	WHERE RMID = @RMID
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_UpdateDiscount
DELIMITER //
CREATE PROCEDURE [dbo].[uspA_UpdateDiscount]
@TransStatus	INT = NULL OUT,
@KID			INT,
@DPID			INT,
@FromDT			DATETIME,
@ToDT			DATETIME,
@Discount		INT
AS
BEGIN
	UPDATE Discounts SET FromDT = @FromDT, ToDT = @ToDT, Discount = @Discount 
	WHERE KID = @KID AND DPID = @DPID;
	--
	IF (@@ERROR = 0 AND @@ROWCOUNT = 1)	SET @TransStatus = 1;
	ELSE	SET @TransStatus = 0;
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_UpdateRestaurantMenu
DELIMITER //
CREATE PROCEDURE [dbo].[uspA_UpdateRestaurantMenu]
@RMID					INT,
@RMCID					INT,
@RID					INT,
@FTID					INT,
@RMName					VARCHAR(64),
@Cost					SMALLMONEY,
@PreparationCost		SMALLMONEY,
@TransStatus			INT = NULL OUT
AS
BEGIN	
	UPDATE RestaurantMenu
	SET RMCID = @RMCID, RID = @RID, FTID = @FTID, RMName = @RMName, Cost = @Cost, PreparationCost = @PreparationCost
	WHERE RMID = @RMID;
	IF (@@ERROR = 0 AND @@ROWCOUNT = 1)	SET @TransStatus = 1
	ELSE SET @TransStatus = 0
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_UpdDP_Paid
DELIMITER //
CREATE PROCEDURE [dbo].[uspA_UpdDP_Paid]
@OrderRef			VARCHAR(64),
@DP_Paid			MONEY,
@TransStatus		INT = NULL OUT
AS
BEGIN
	DECLARE @KOID INT = 0;
	SET @KOID = (SELECT KOID FROM KitchenOrders WHERE OrderRef = @OrderRef);
	IF(@KOID = 0)
	BEGIN
		SET @TransStatus = 2; -- NO ORDER FOUND		
	END
	ELSE
	BEGIN
		UPDATE KitchenOrders SET DP_Paid = @DP_Paid WHERE KOID = @KOID;
		IF (@@ERROR = 0 AND @@ROWCOUNT = 1)	SET @TransStatus = 1
		ELSE	SET @TransStatus = 0
	END	
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_UpdKitchenOrderFlag
DELIMITER //
CREATE PROCEDURE [dbo].[uspA_UpdKitchenOrderFlag]
@TransStatus		INT = NULL OUT,
@KOID				INT,
@Flag				CHAR(02)
AS
BEGIN
	UPDATE KitchenOrders SET Flag = @Flag WHERE KOID = @KOID;
	IF (@@ERROR = 0 AND @@ROWCOUNT = 1)	SET @TransStatus = 1
	ELSE	SET @TransStatus = 0
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_UpdMenu
DELIMITER //
CREATE PROCEDURE [dbo].[uspA_UpdMenu]
@TransStatus			INT = NULL OUT,
@MID					INT,
@MCID					INT,
@FTID					INT,
@MName					VARCHAR(64),
@MOrder					INT,
@RegularPrice			SMALLMONEY,
@SwiggyProposedPrice	SMALLMONEY = NULL,
@isActive				BIT
AS
BEGIN
	UPDATE Menu SET MCID = @MCID, FTID = @FTID, MName = @MName, MOrder = @MOrder, 
					RegularPrice = @RegularPrice, SwiggyProposedPrice = @SwiggyProposedPrice, isActive = @isActive
	WHERE MID= @MID;
	--
	IF (@@ERROR = 0 AND @@ROWCOUNT = 1)	SET @TransStatus = 1
	ELSE	SET @TransStatus = 0
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_UpdOrder
DELIMITER //
CREATE PROCEDURE [dbo].[uspA_UpdOrder]
@OID						INT,
@OSID						INT,
@TransStatus				INT = NULL OUT
AS
BEGIN
	UPDATE Orders SET OSID = @OSID WHERE OID = @OID
	--
	IF (@@ERROR = 0 AND @@ROWCOUNT = 1)	SET @TransStatus = 1
	ELSE	SET @TransStatus = 0
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_UpdStockItem
DELIMITER //
--SELECT * FROM StockItems
CREATE PROCEDURE [dbo].[uspA_UpdStockItem]
@TransStatus		INT = NULL OUT,
@SIID				INT,
@SIName				VARCHAR(64),
@Unit				CHAR(02)
AS
BEGIN
	UPDATE StockItems SET SIName = @SIName, Unit = @Unit WHERE SIID = @SIID;
	IF (@@ERROR = 0 AND @@ROWCOUNT = 1)	SET @TransStatus = 1
	ELSE	SET @TransStatus = 0
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.usp_CreateRestaurantMenu
DELIMITER //
CREATE PROCEDURE [dbo].[usp_CreateRestaurantMenu]
@RMCID					INT,
@RID					INT,
@FTID					INT,
@RMName					VARCHAR(64),
@Cost					SMALLMONEY,
@PreparationCost		SMALLMONEY,
@RMID					INT = NULL OUT,
@TransStatus			INT = NULL OUT
AS
BEGIN
	DECLARE @TblRestaurantMenu TABLE (RMID INT);
	INSERT INTO RestaurantMenu	(RMCID, RID, FTID, RMName, Cost, PreparationCost) OUTPUT inserted.RMID INTO @TblRestaurantMenu
	VALUES (@RMCID, @RID, @FTID, @RMName, @Cost, @PreparationCost)
	SET @RMID = (SELECT RMID FROM @TblRestaurantMenu);
	IF (@@ERROR = 0 AND @@ROWCOUNT = 1)	SET @TransStatus = 1
	ELSE	SET @TransStatus = 0
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.usp_InsNewOrder
DELIMITER //
CREATE PROCEDURE [dbo].[usp_InsNewOrder]
@OID				INT = NULL OUT,
@TransStatus		INT = NULL OUT,
@OMenu				VARCHAR(MAX),
@OTotal				SMALLMONEY,
@PayingBy			VARCHAR(64),
@NeedChangeFor		VARCHAR(8) = NULL,
@ONotes				VARCHAR(564) = NULL,
@CxName				VARCHAR(64),
@CxMobile			VARCHAR(10),
@DeliveryLocation	VARCHAR(256),
@DeliveryTime		VARCHAR(64)
AS
BEGIN
	DECLARE @TblOrders TABLE (OID INT)
	INSERT INTO ORDERS (OMenu, OTotal, PayingBy, NeedChangeFor, ONotes, OSID, CxName, CxMobile, DeliveryLocation, DeliveryTime, CrDT, UpDT) OUTPUT inserted.OID INTO @TblOrders
	VALUES(@OMenu, @OTotal, @PayingBy, @NeedChangeFor, @ONotes, 1, @CxName, @CxMobile, @DeliveryLocation, @DeliveryTime, dbo.GetLocalDate(), NULL)
	--
	SELECT @OID = OID FROM @TblOrders
	IF (@@ERROR = 0 AND @@ROWCOUNT = 1)	SET @TransStatus = 1
	ELSE	SET @TransStatus = 0
END
//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_ReadAliasing
DELIMITER //
CREATE PROCEDURE uspA_ReadAliasing
AS
BEGIN
	SELECT Swiggy, FoodPanda, UberEats, Zomato , ZomatoBVP
	FROM Aliasing
END//
DELIMITER ;

-- Dumping structure for procedure FFU.uspA_ReadPOP_Stats
DELIMITER //
CREATE PROCEDURE [ffu_admin].[uspA_ReadPOP_Stats]
@DPID				INT,
@KID				INT,
@FromDT				DATETIME,
@ToDT				DATETIME
AS
BEGIN
	SELECT M.MName, SUM(KOD.Qty) [Qty], SUM(KOD.Total) [Total] FROM Menu M
	JOIN KitchenOrderData KOD ON M.MID = KOD.MID
	WHERE KOD.KOID IN (SELECT KO.KOID FROM KitchenOrders KO WHERE KO.KoTime BETWEEN @FromDT AND @ToDT AND KO.DPID = @DPID AND KO.KID = @KID)
	GROUP BY M.MName
END
//
DELIMITER ;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
