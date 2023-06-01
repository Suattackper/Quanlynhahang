USE quanly;

CREATE TABLE quyen (
	maquyen INT NOT NULL PRIMARY KEY,
	tenquyen NVARCHAR(50) NOT NULL
);
-- COLLATE SQL_Latin1_General_CP1_CS_AS lệnh dùng để phân biệt hoa thường trong sqlserver
CREATE TABLE nguoidung (
	manguoidung NVARCHAR(50) COLLATE SQL_Latin1_General_CP1_CS_AS NOT NULL PRIMARY KEY,
	matkhau NVARCHAR(50) COLLATE SQL_Latin1_General_CP1_CS_AS NOT NULL,
	hovaten NVARCHAR(50) NOT NULL,
	email NVARCHAR(50) NOT NULL UNIQUE,
	maquyen INT DEFAULT 0 NOT NULL ,
	ngaytao DATE DEFAULT GETDATE() NOT NULL CHECK (ngaytao > DATEADD(DAY, -1, GETDATE()) AND ngaytao < DATEADD(DAY, 1, GETDATE())),
	maxacnhan NVARCHAR(50),
	FOREIGN KEY (maquyen) REFERENCES quyen(maquyen)
);

CREATE TABLE khachhang (
	sodienthoai NVARCHAR(50) NOT NULL PRIMARY KEY,
	solan INT NOT NULL DEFAULT 1 CHECK (solan > 0)
);

CREATE TABLE loai (
	maloai INT NOT NULL PRIMARY KEY,
	tenloai NVARCHAR(50) NOT NULL
);

CREATE TABLE sanpham (
	masanpham NVARCHAR(50) COLLATE SQL_Latin1_General_CP1_CS_AS NOT NULL PRIMARY KEY,
	tensanpham NVARCHAR(50) NOT NULL,
	maloai INT NOT NULL DEFAULT 0,
	gia INT NOT NULL,
	mota NVARCHAR(50) DEFAULT N'Không có mô t?',
	anh IMAGE,
	ngaythem DATE NOT NULL DEFAULT GETDATE() CHECK (ngaythem > DATEADD(DAY, -1, GETDATE()) AND ngaythem < DATEADD(DAY, 1, GETDATE())),
	FOREIGN KEY (maloai) REFERENCES loai(maloai)
);

CREATE TABLE ban (
	maban NVARCHAR(50) COLLATE SQL_Latin1_General_CP1_CS_AS NOT NULL PRIMARY KEY,
	tenban NVARCHAR(50) NOT NULL
);

CREATE TABLE khuyenmai (
	makhuyenmai NVARCHAR(50) COLLATE SQL_Latin1_General_CP1_CS_AS NOT NULL PRIMARY KEY,
	tenkhuyenmai NVARCHAR(50) NOT NULL,
	soluong INT NOT NULL DEFAULT 1,
	dieukien INT NOT NULL,
	ngaybatdau DATE NOT NULL DEFAULT GETDATE() CHECK (ngaybatdau > DATEADD(DAY, -1, GETDATE())),
	ngayketthuc DATE NOT NULL,
	CONSTRAINT CK_ngayketthuc CHECK (ngayketthuc > ngaybatdau)
);

CREATE TABLE hoadon (
	mahoadon int IDENTITY(1,1) PRIMARY KEY,
	manguoidung NVARCHAR(50) COLLATE SQL_Latin1_General_CP1_CS_AS NOT NULL,
	maban NVARCHAR(50) COLLATE SQL_Latin1_General_CP1_CS_AS NOT NULL,
	makhuyenmai NVARCHAR(50) COLLATE SQL_Latin1_General_CP1_CS_AS,
	yeucauthem NVARCHAR(200),
	ngaytao DATE NOT NULL DEFAULT GETDATE() CHECK (ngaytao > DATEADD(DAY, -1, GETDATE()) AND ngaytao < DATEADD(DAY, 1, GETDATE())),
	FOREIGN KEY (manguoidung) REFERENCES nguoidung(manguoidung),
	FOREIGN KEY (maban) REFERENCES ban(maban),
	FOREIGN KEY (makhuyenmai) REFERENCES khuyenmai(makhuyenmai)
);

CREATE TABLE chitietsanpham (
	mahoadon int NOT NULL,
	masanpham NVARCHAR(50) COLLATE SQL_Latin1_General_CP1_CS_AS NOT NULL,
	soluong int NOT NULL default 1 check (soluong >0),
	PRIMARY KEY (mahoadon, masanpham),
	FOREIGN KEY (mahoadon) REFERENCES hoadon(mahoadon),
	FOREIGN KEY (masanpham) REFERENCES sanpham(masanpham)
);