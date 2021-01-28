CREATE DATABASE QLBH;
USE QLBH;

CREATE TABLE TaiKhoan ( 
	TenDangNhap varchar(50) NOT NULL,
	MatKhau varchar(50) NOT NULL,
	HoTen varchar(100) NOT NULL,
	Email varchar(100) NOT NULL,
	QRCode varchar(100) NOT NULL,
	VaiTro boolean DEFAULT 0 NOT NULL,
	PRIMARY KEY (TenDangNhap)
);

CREATE TABLE HangSanXuat (
	MaHangSX int NOT NULL AUTO_INCREMENT,
	TenHangSX varchar(50) NOT NULL,
 	PRIMARY KEY (MaHangSX)
);

CREATE TABLE LoaiSanPham (
	MaLoaiSP int NOT NULL AUTO_INCREMENT,
	TenLoaiSP varchar(50) NOT NULL,
 	PRIMARY KEY (MaLoaiSP)
);

CREATE TABLE SanPham (
	MaSP int NOT NULL AUTO_INCREMENT,
	TenSP varchar(100) NOT NULL,
	LoaiSP int NOT NULL,
	HangSX int NOT NULL,
	GiaNhap bigint NOT NULL,
	GiaBan bigint NOT NULL,
	TonKho int NOT NULL,
	TrangThai boolean DEFAULT 1 NOT NULL,
	Hinh varchar(100) NOT NULL,
	MoTa varchar(250),
	PRIMARY KEY (MaSP),
	FOREIGN KEY (LoaiSP) REFERENCES LoaiSanPham(MaLoaiSP)
	ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (HangSX) REFERENCES HangSanXuat(MaHangSX)
	ON UPDATE CASCADE ON DELETE CASCADE,
	CHECK (GiaNhap > 0 AND GiaBan > 0 AND TonKho >=0)
);

CREATE TABLE NhaPhanPhoi (
	MaNPP int NOT NULL AUTO_INCREMENT,
	TenNPP varchar(100) NOT NULL,
	DiaChi varchar(100) NOT NULL,
	SoDT varchar(11) NOT NULL,
	Email varchar(100) NOT NULL,
 	PRIMARY KEY (MaNPP)
);

CREATE TABLE LoaiKhachHang (
	MaLoaiKH int NOT NULL AUTO_INCREMENT,
	TenLoaiKH varchar(50) NOT NULL,
	PRIMARY KEY (MaLoaiKH)
);

CREATE TABLE KhachHang (
	MaKH int NOT NULL AUTO_INCREMENT,
	TenKH varchar(100) NOT NULL,
	NgaySinh date,
	GioiTinh boolean NOT NULL,
	DiaChi varchar(100),
	SoDT varchar(10) NOT NULL,
	LoaiKH int DEFAULT 1 NOT NULL,
 	PRIMARY KEY (MaKH),
 	FOREIGN KEY (LoaiKH) REFERENCES LoaiKhachHang(MaLoaiKH)
	ON UPDATE CASCADE ON DELETE CASCADE
); 

CREATE TABLE PhieuNhap (
	MaPN int NOT NULL AUTO_INCREMENT,
	MaNV varchar(50) NOT NULL,
	MaNPP int NOT NULL,
	TongTien bigint NOT NULL,
	NgayNhap date DEFAULT CURRENT_DATE,
	GhiChu varchar(250),
	PRIMARY KEY (MaPN),
	FOREIGN KEY (MaNV) REFERENCES TaiKhoan(TenDangNhap)
	ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (MaNPP) REFERENCES NhaPhanPhoi(MaNPP)
	ON UPDATE CASCADE ON DELETE CASCADE,
	CHECK (TongTien > 0)
);

CREATE TABLE ChiTietPhieuNhap (
	MaPN int NOT NULL,
	MaSP int NOT NULL,
	SoLuong int NOT NULL,
	DonGia bigint NOT NULL,
	ThanhTien bigint NOT NULL,
 	PRIMARY KEY (MaPN, MaSP),
 	FOREIGN KEY (MaPN) REFERENCES PhieuNhap(MaPN)
	ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (MaSP) REFERENCES SanPham(MaSP)
	ON UPDATE CASCADE ON DELETE CASCADE,
	CHECK (SoLuong > 0 AND DonGia > 0 AND ThanhTien > 0)
); 

CREATE TABLE HoaDon (
	MaHD int NOT NULL AUTO_INCREMENT,
	MaNV varchar(50) NOT NULL,
	MaKH int NOT NULL,
	TongTien bigint NOT NULL,
	NgayBan date DEFAULT CURRENT_DATE,
	GhiChu varchar(250),
	PRIMARY KEY (MaHD),
	FOREIGN KEY (MaNV) REFERENCES TaiKhoan(TenDangNhap)
	ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH)
	ON UPDATE CASCADE ON DELETE CASCADE,
	CHECK (TongTien > 0)
);

CREATE TABLE ChiTietHoaDon (
	MaHD int NOT NULL,
	MaSP int NOT NULL,
	SoLuong int NOT NULL,
	DonGia bigint NOT NULL,
	GiamGia int DEFAULT 0 NOT NULL,
	ThanhTien bigint NOT NULL,
	PRIMARY KEY (MaHD, MaSP),
 	FOREIGN KEY (MaHD) REFERENCES HoaDon(MaHD)
	ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (MaSP) REFERENCES SanPham(MaSP)
	ON UPDATE CASCADE ON DELETE CASCADE,
	CHECK (SoLuong > 0 AND DonGia > 0 AND GiamGia >= 0 AND ThanhTien > 0)
);

INSERT INTO TaiKhoan (TenDangNhap, MatKhau, HoTen, Email, QRCode, VaiTro) VALUES
	('kietnt','kietnt','Tuấn Kiệt','kietnt@mail.com','kietnt.png',1),
	('duyna','duyna','Anh Duy','duyna@mail.com','duyna.png',0),
	('myntp','myntp','Phú Mỹ','myntp@mail.com','myntp.png',0),
	('anhld','anhld','Duẩn Anh','anhld@mail.com','anhld.png',0);

INSERT INTO HangSanXuat (TenHangSX) VALUES
	('Apple'),
	('Samsung'),
	('Sony'),
	('Huawei'),
	('Asus'),
	('HP'),
	('Casio'),
	('Tag Heuer'),
	('Omega');

INSERT INTO LoaiSanPham (TenLoaiSP) VALUES
	('Điện thoại'),
	('Máy tính bảng'),
	('Laptop'),
	('Đồng hồ');

INSERT INTO SanPham (TenSP, LoaiSP, HangSX, GiaNhap, GiaBan, TonKho, TrangThai, Hinh, MoTa) VALUES
	('iPhone 11 Pro',1,1,15000000,20000000,15,1,'ip.png',''),
	('Samsung Note 10',1,2,12000000,17000000,12,1,'ss.png',''),
	('Sony XPeria Z',1,3,13000000,18000000,13,1,'sn.png',''),
	('iPad Retina Pro',2,1,16000000,21000000,8,1,'ipd.png',''),
	('Samsung Galaxy Tab',2,2,14000000,18000000,11,1,'sst.png',''),
	('Huawei Mate Pro',2,4,13000000,16000000,13,1,'hw.png',''),
	('Asus VivoBook',3,5,16000000,19000000,12,1,'as.png',''),
	('HP Omen 15',3,6,17000000,21000000,9,1,'hp.png',''),
	('MacBook Pro',3,1,18000000,25000000,10,1,'mb.png',''),
	('Casio G-Shock',4,7,6500000,7500000,9,1,'cs.png',''),
	('Tag Heuer Men',4,8,9000000,9500000,7,1,'th.png',''),
	('Omega Prestige',4,9,8500000,9000000,8,1,'om.png','');

INSERT INTO NhaPhanPhoi (TenNPP, DiaChi, SoDT, Email) VALUES
	('Công ty Viễn Sơn','Quận 3','02873008888','vienson@vs.com'),
	('Công ty Digital World','Quận 10','02877008888','digiworld@dg.com'),
	('Công ty Phong Vũ','Quận Tân Bình','02875008888','phongvu@pv.com'),
	('Công ty TechZones','Quận Phú Nhuận','02878008888','techzones@tz.com');

INSERT INTO LoaiKhachHang (TenLoaiKH) VALUES
	('Khách hàng thường'),
	('Khách hàng thân thiết'),
	('Khách hàng VIP');

INSERT INTO KhachHang (TenKH, NgaySinh, GioiTinh, DiaChi, SoDT, LoaiKH) VALUES
	('Khách vãng lai',null,1,'','',1),
	('Lê Hoàng Luân','1996-06-12',1,'Quận 2','0963685468',3),
	('Chu Tĩnh Như','1999-09-03',0,'Quận 7','0978684366',1),
	('Trần Linh Trang','1997-11-07',0,'Quận 11','0968680345',3),
	('Hồ Hồng Nga','1988-05-26',0,'Quận Gò Vấp','0945365765',1),
	('Lê Hoài Phương','1995-02-08',0,'Quận Thủ Đức','0919129139',2),
	('Hà Anh Tuấn','1991-12-11',1,'Quận Phú Nhuận','0977768786',1),
	('Nguyễn Hoàng Nam','1999-03-19',1,'Quận 3','0933588778',1),
	('Trần Duy Anh','1994-04-08',1,'Quận 12','0976316776',2),
	('Phan Nhã Trân','2000-07-05',0,'Quận Bình Tân','0967783768',3),
	('Đặng Minh Quân','1998-08-13',1,'Quận Bình Thạnh','0987664668',1),
	('Lê Đông Phong','1987-10-17',1,'Quận 3','0988983389',1),
	('Cao Ngọc Huyền','1998-09-11',0,'Quận 5','0938376868',1),
	('Ngô Như Quỳnh','1999-10-03',0,'Quận 11','0988685068',1),
	('Lý Anh Tuấn','1990-04-03',1,'Quận Thủ Đức','0938687569',1),
	('Võ Hoàng Hải','1993-07-21',1,'Quận Bình Thạnh','0918688468',1);

INSERT INTO PhieuNhap (MaNV, MaNPP, TongTien, NgayNhap, GhiChu) VALUES
	('anhld',1,96000000,'2020-01-28','Chín mươi sáu triệu đồng'),
	('anhld',3,39000000,'2020-02-10','Ba mươi chín triệu đồng'),
	('duyna',2,39000000,'2020-03-22','Ba mươi chín triệu đồng'),
	('myntp',3,34000000,'2020-04-19','Ba mươi bốn triệu đồng'),
	('myntp',4,36000000,'2020-05-07','Ba mươi sáu triệu đồng'),
	('duyna',2,101000000,'2020-06-12','Một trăm lẻ một triệu đồng'),
	('duyna',4,72000000,'2020-07-27','Bảy mươi hai triệu đồng'),
	('myntp',1,36000000,'2020-08-16','Ba mươi sáu triệu đồng');

INSERT INTO ChiTietPhieuNhap (MaPN, MaSP, SoLuong, DonGia, ThanhTien) VALUES
	(1,2,4,12000000,48000000),
	(1,4,3,16000000,48000000),
	(2,10,6,6500000,39000000),
	(3,6,3,13000000,39000000),
	(4,12,4,8500000,34000000),
	(5,9,2,18000000,36000000),
	(6,1,5,15000000,75000000),
	(6,10,4,6500000,26000000),
	(7,9,4,18000000,72000000),
	(8,2,3,12000000,36000000);

INSERT INTO HoaDon (MaNV, MaKH, TongTien, NgayBan, GhiChu) VALUES
	('anhld',2,19400000,'2020-01-10','Mười chín triệu bốn trăm ngàn đồng'),
	('duyna',4,30520000,'2020-01-23','Ba mươi triệu năm trăm hai mươi ngàn đồng'),
	('duyna',7,9000000,'2020-01-31','Chín triệu đồng'),
	('anhld',8,9500000,'2020-02-08','Chín triệu năm trăm ngàn đồng'),
	('myntp',10,23750000,'2020-02-16','Hai mươi ba triệu bảy trăm năm mươi ngàn đồng'),
	('myntp',2,36000000,'2020-02-22','Ba mươi sáu triệu đồng'),
	('duyna',9,36000000,'2020-03-17','Ba mươi sáu triệu đồng'),
	('anhld',13,21000000,'2020-03-26','Hai mươi mốt triệu đồng'),
	('duyna',6,27500000,'2020-04-09','Hai mươi bảy triệu năm trăm ngàn đồng'),
	('anhld',4,32000000,'2020-04-18','Ba mươi hai triệu đồng'),
	('duyna',5,19000000,'2020-05-11','Mười chín triệu đồng'),
	('myntp',11,20370000,'2020-05-27','Hai mươi triệu ba trăm bảy mươi ngàn đồng'),
	('myntp',2,17460000,'2020-06-01','Mười bảy triệu bốn trăm sáu mươi ngàn đồng'),
	('anhld',15,27500000,'2020-06-29','Hai mươi bảy triệu năm trăm ngàn đồng'),
	('anhld',12,18000000,'2020-07-07','Mười tám triệu đồng'),
	('duyna',14,25000000,'2020-07-23','Hai mươi lăm triệu đồng'),
	('myntp',3,16000000,'2020-08-01','Mười sáu triệu đồng'),
	('myntp',10,28500000,'2020-08-15','Hai mươi tám triệu năm trăm ngàn đồng'),
	('duyna',6,21000000,'2020-08-21','Hai mươi mốt triệu đồng'),
	('duyna',16,23750000,'2020-09-02','Hai mươi ba triệu bảy trăm năm mươi ngàn đồng');

INSERT INTO ChiTietHoaDon (MaHD, MaSP, SoLuong, DonGia, GiamGia, ThanhTien) VALUES
	(1,1,1,20000000,3,19400000),
	(2,6,1,16000000,3,15520000),
	(2,10,2,7500000,0,15000000),
	(3,12,1,9000000,0,9000000),
	(4,11,1,9500000,0,9500000),
	(5,9,1,25000000,5,23750000),
	(6,2,1,17000000,0,17000000),
	(6,7,1,19000000,0,19000000),
	(7,5,2,18000000,0,36000000),
	(8,8,1,21000000,0,21000000),
	(9,1,1,20000000,0,20000000),
	(9,10,1,7500000,0,7500000),
	(10,6,2,16000000,0,32000000),
	(11,11,2,9500000,0,19000000),
	(12,4,1,21000000,3,20370000),
	(13,12,2,9000000,3,17460000),
	(14,1,1,20000000,0,20000000),
	(14,10,1,7500000,0,7500000),
	(15,3,1,18000000,0,18000000),
	(16,9,1,25000000,0,25000000),
	(17,6,1,16000000,0,16000000),
	(18,7,1,19000000,0,19000000),
	(18,11,1,9500000,0,9500000),
	(19,8,1,21000000,0,21000000),
	(20,9,1,25000000,5,23750000);

-- Trigger
CREATE TRIGGER HSX_MaHangSX BEFORE INSERT ON HangSanXuat
FOR EACH ROW SET NEW.MaHangSX = IF((SELECT COUNT(*) FROM HangSanXuat)=0, 1, (SELECT MAX(MaHangSX) FROM HangSanXuat)+1);
CREATE TRIGGER LSP_MaLoaiSP BEFORE INSERT ON LoaiSanPham
FOR EACH ROW SET NEW.MaLoaiSP = IF((SELECT COUNT(*) FROM LoaiSanPham)=0, 1, (SELECT MAX(MaLoaiSP) FROM LoaiSanPham)+1);
CREATE TRIGGER SP_MaSP BEFORE INSERT ON SanPham
FOR EACH ROW SET NEW.MaSP = IF((SELECT COUNT(*) FROM SanPham)=0, 1, (SELECT MAX(MaSP) FROM SanPham)+1);
CREATE TRIGGER NPP_MaNPP BEFORE INSERT ON NhaPhanPhoi
FOR EACH ROW SET NEW.MaNPP = IF((SELECT COUNT(*) FROM NhaPhanPhoi)=0, 1, (SELECT MAX(MaNPP) FROM NhaPhanPhoi)+1);
CREATE TRIGGER LKH_MaLoaiKH BEFORE INSERT ON LoaiKhachHang
FOR EACH ROW SET NEW.MaLoaiKH = IF((SELECT COUNT(*) FROM LoaiKhachHang)=0, 1, (SELECT MAX(MaLoaiKH) FROM LoaiKhachHang)+1);
CREATE TRIGGER KH_MaKH BEFORE INSERT ON KhachHang
FOR EACH ROW SET NEW.MaKH = IF((SELECT COUNT(*) FROM KhachHang)=0, 1, (SELECT MAX(MaKH) FROM KhachHang)+1);
CREATE TRIGGER PN_MaPN BEFORE INSERT ON PhieuNhap
FOR EACH ROW SET NEW.MaPN = IF((SELECT COUNT(*) FROM PhieuNhap)=0, 1, (SELECT MAX(MaPN) FROM PhieuNhap)+1);
CREATE TRIGGER HD_MaHD BEFORE INSERT ON HoaDon
FOR EACH ROW SET NEW.MaHD = IF((SELECT COUNT(*) FROM HoaDon)=0, 1, (SELECT MAX(MaHD) FROM HoaDon)+1);

CREATE TRIGGER PhieuNhap_NgayNhap BEFORE INSERT ON PhieuNhap
FOR EACH ROW SET NEW.NgayNhap = IFNULL(NEW.NgayNhap, CURDATE());
CREATE TRIGGER HoaDon_NgayBan BEFORE INSERT ON HoaDon
FOR EACH ROW SET NEW.NgayBan = IFNULL(NEW.NgayBan, CURDATE());

-- View
CREATE VIEW TKKhachHang AS
	SELECT TenKH TenKhachHang,
		SoDT SoDienThoai,
		COUNT(*) LuotMua,
		SUM(TongTien) TongTien 
	FROM HoaDon hd
	JOIN KhachHang kh ON kh.MaKH = hd.MaKH
	GROUP BY TenKhachHang, SoDienThoai
	ORDER BY TongTien DESC;

-- SELECT * FROM TKKhachHang;

-- StoredProcedure
DELIMITER //
DROP PROCEDURE IF EXISTS sp_NhapVao //
CREATE PROCEDURE sp_NhapVao (IN mapn INT, masp INT, soluong INT, dongia BIGINT, thanhtien BIGINT)
BEGIN
  	INSERT INTO ChiTietPhieuNhap (MaPN, MaSP, SoLuong, DonGia, ThanhTien) VALUES
  		(mapn,masp,soluong,dongia,thanhtien);
	UPDATE SanPham sp SET TonKho = TonKho + soluong WHERE sp.MaSP = masp;
	IF (SELECT TonKho FROM SanPham sp WHERE sp.MaSP = masp) > 0 THEN
   		UPDATE SanPham sp SET TrangThai = 1 WHERE sp.MaSP = masp;
	END IF;
END //
DELIMITER;

-- CALL sp_NhapVao(8,11,7,9000000,63000000);

DELIMITER //
DROP PROCEDURE IF EXISTS sp_BanRa //
CREATE PROCEDURE sp_BanRa (IN mahd INT, masp INT, soluong INT, dongia BIGINT, giamgia INT, thanhtien BIGINT)
BEGIN
  	INSERT INTO ChiTietHoaDon (MaHD, MaSP, SoLuong, DonGia, GiamGia, ThanhTien) VALUES
  		(mahd,masp,soluong,dongia,giamgia,thanhtien);
	UPDATE SanPham sp SET TonKho = TonKho - soluong WHERE sp.MaSP = masp;
	IF (SELECT TonKho FROM SanPham sp WHERE sp.MaSP = masp) = 0 THEN
   		UPDATE SanPham sp SET TrangThai = 0 WHERE sp.MaSP = masp;
	END IF;
END //
DELIMITER;

-- CALL sp_BanRa(20,11,7,9500000,0,66500000);

DELIMITER //
DROP PROCEDURE IF EXISTS sp_TKNhapVao //
CREATE PROCEDURE sp_TKNhapVao (IN fromdate DATE, todate DATE)
BEGIN
  	SET fromdate = IFNULL(fromdate, (SELECT MIN(NgayNhap) FROM PhieuNhap));
	SET todate = IFNULL(todate, (SELECT MAX(NgayNhap) FROM PhieuNhap));

	SELECT ctpn.MaSP MaSanPham, 
		TenSP TenSanPham, 
		SUM(SoLuong) SoLuong, 
		SUM(ThanhTien) ThanhTien 
	FROM ChiTietPhieuNhap ctpn
	JOIN PhieuNhap pn ON pn.MaPN = ctpn.MaPN
	JOIN SanPham sp ON sp.MaSP = ctpn.MaSP
	WHERE NgayNhap BETWEEN fromdate AND todate
	GROUP BY ctpn.MaSP, TenSP
	ORDER BY SoLuong DESC;
END //
DELIMITER;

-- CALL sp_TKNhapVao('2020-01-01','2020-09-01');

DELIMITER //
DROP PROCEDURE IF EXISTS sp_TKBanRa //
CREATE PROCEDURE sp_TKBanRa (IN fromdate DATE, todate DATE)
BEGIN
  	SET fromdate = IFNULL(fromdate, (SELECT MIN(NgayBan) FROM HoaDon));
	SET todate = IFNULL(todate, (SELECT MAX(NgayBan) FROM HoaDon));

	SELECT cthd.MaSP MaSanPham, 
		TenSP TenSanPham, 
		SUM(SoLuong) SoLuong, 
		SUM(ThanhTien) ThanhTien 
	FROM ChiTietHoaDon cthd
	JOIN HoaDon hd ON hd.MaHD = cthd.MaHD
	JOIN SanPham sp ON sp.MaSP = cthd.MaSP
	WHERE NgayBan BETWEEN fromdate AND todate
	GROUP BY cthd.MaSP, TenSP
	ORDER BY SoLuong DESC;
END //
DELIMITER;

-- CALL sp_TKBanRa('2020-01-01','2020-09-01');

DELIMITER //
DROP PROCEDURE IF EXISTS sp_DoanhThuThang //
CREATE PROCEDURE sp_DoanhThuThang (IN fromdate DATE, todate DATE)
BEGIN
  	SET fromdate = IFNULL(fromdate, (SELECT MIN(NgayBan) FROM HoaDon));
	SET todate = IFNULL(todate, (SELECT MAX(NgayBan) FROM HoaDon));

	SELECT CONCAT(MONTH(NgayBan),'/',YEAR(NgayBan)) Thang,
		SUM(TongTien) DoanhThu
	FROM HoaDon
	WHERE NgayBan BETWEEN fromdate AND todate
	GROUP BY Thang;
END //
DELIMITER;

-- CALL sp_DoanhThuThang('2020-01-01','2020-09-01');

DELIMITER //
DROP PROCEDURE IF EXISTS sp_DoanhThuNgay //
CREATE PROCEDURE sp_DoanhThuNgay (IN fromdate DATE, todate DATE)
BEGIN
  	SET fromdate = IFNULL(fromdate, (SELECT MIN(NgayBan) FROM HoaDon));
	SET todate = IFNULL(todate, (SELECT MAX(NgayBan) FROM HoaDon));

	SELECT NgayBan,
		SUM(TongTien) DoanhThu
	FROM HoaDon
	WHERE NgayBan BETWEEN fromdate AND todate
	GROUP BY NgayBan;
END //
DELIMITER;

-- CALL sp_DoanhThuNgay('2020-01-01','2020-09-01');