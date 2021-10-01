INSERT INTO Vendor (Address1,City,Province,PostalCode,Phone,Type,Name,Email)VALUES ('123 Maple St','London','On', 'N1N-1N1','(555)555-5555','Trusted','ABC Supply Co.','abc@supply.com');
INSERT INTO Vendor (Address1,City,Province,PostalCode,Phone,Type,Name,Email) VALUES ('543 Sycamore Ave','Toronto','On', 'N1P-1N1','(999)555-5555','Trusted','Big Bills Depot','bb@depot.com');
INSERT INTO Vendor (Address1,City,Province,PostalCode,Phone,Type,Name,Email) VALUES ('922 Oak St','London','On', 'N1N-1N1','(555)555-5599','Trusted','Shady Sams','ss@underthetable.com');
INSERT INTO Vendor (Address1,City,Province,PostalCode,Phone,Type,Name,Email) VALUES ('922 Oak St','London','On', 'N1N-1N1','(555)555-5599','Trusted','Austin Demelo','ss@underthetable.com');

INSERT INTO Product (Id, vendorid, name, costprice, msrp, rop, eoq, qoh, qoo, qrcode, qrcodetxt) VALUES ('1n34w9', 1, 'Cuban Cigars', 10.0, 15.0, 35, 70, 40, 40, 0101010, 'qrcodetext');
INSERT INTO Product (Id, vendorid, name, costprice, msrp, rop, eoq, qoh, qoo, qrcode, qrcodetxt) VALUES ('1t23x7', 2, 'Columbian Cigars', 100.0, 150.0, 3, 7, 4, 4, 0101010, 'qrcodetext');
INSERT INTO Product (Id, vendorid, name, costprice, msrp, rop, eoq, qoh, qoo, qrcode, qrcodetxt) VALUES ('1s23s4', 3, 'Hondoras Cigars', 1.0, 5.0, 13, 17, 14, 4, 0101010, 'qrcodetext');
INSERT INTO Product (Id, vendorid, name, costprice, msrp, rop, eoq, qoh, qoo, qrcode, qrcodetxt) VALUES ('3s45f2', 4, 'Venezulan Cigars', 15.0, 17.0, 23, 27, 4, 14, 0101010, 'qrcodetext');
INSERT INTO Product (Id, vendorid, name, costprice, msrp, rop, eoq, qoh, qoo, qrcode, qrcodetxt) VALUES ('5a57s3', 4, 'American Cigars', 5.0, 10.0, 23, 27, 4, 14, 0101010, 'qrcodetext');
INSERT INTO Product (Id, vendorid, name, costprice, msrp, rop, eoq, qoh, qoo, qrcode, qrcodetxt) VALUES ('3d52d2', 4, 'Nicaraguan Cigars', 10.0, 11.0, 23, 27, 4, 14, 0101010, 'qrcodetext');


