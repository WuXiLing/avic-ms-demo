
  CREATE TABLE "STOCK" 
   (	"ID" VARCHAR2(50 BYTE) NOT NULL ENABLE, 
	"GOODS_ID" VARCHAR2(50 BYTE), 
	"NUM" NUMBER(*,0), 
	"ORG_ID" VARCHAR2(50 BYTE), 
	"DEPT_ID" VARCHAR2(50 BYTE), 
	"SYS_ID" VARCHAR2(50 BYTE), 
	"SECRET_LEVEL" VARCHAR2(50 BYTE), 
	"SYS_APPLICATION_ID" VARCHAR2(50 BYTE), 
	"CREATED_BY" VARCHAR2(50 BYTE), 
	"CREATION_DATE" DATE, 
	"LAST_UPDATED_BY" VARCHAR2(50 BYTE), 
	"LAST_UPDATE_DATE" DATE, 
	"LAST_UPDATE_IP" VARCHAR2(20 BYTE), 
	"VERSION" NUMBER(16,0), 
	"ATTRIBUTE_01" VARCHAR2(255 BYTE), 
	"ATTRIBUTE_02" VARCHAR2(255 BYTE), 
	"ATTRIBUTE_03" VARCHAR2(255 BYTE), 
	"ATTRIBUTE_04" VARCHAR2(255 BYTE), 
	"ATTRIBUTE_05" VARCHAR2(255 BYTE), 
	"ATTRIBUTE_06" VARCHAR2(255 BYTE), 
	"ATTRIBUTE_07" VARCHAR2(255 BYTE), 
	"ATTRIBUTE_08" VARCHAR2(255 BYTE), 
	"ATTRIBUTE_09" DATE, 
	"ATTRIBUTE_10" DATE, 
	"ATTRIBUTE_11" NUMBER(*,0), 
	"ATTRIBUTE_12" NUMBER(*,0), 
	"ATTRIBUTE_13" NUMBER(*,0), 
	"ATTRIBUTE_14" NUMBER(*,0), 
	"ATTRIBUTE_15" NUMBER(*,0), 
	 CONSTRAINT "PK_STOCK" PRIMARY KEY ("ID")
   ) ;
 

   COMMENT ON COLUMN "STOCK"."ID" IS 'id';
 
   COMMENT ON COLUMN "STOCK"."GOODS_ID" IS '商品id';
 
   COMMENT ON COLUMN "STOCK"."NUM" IS '库存数量';
 
   COMMENT ON COLUMN "STOCK"."ORG_ID" IS '组织ID';
 
   COMMENT ON COLUMN "STOCK"."DEPT_ID" IS '部门ID';
 
   COMMENT ON COLUMN "STOCK"."SYS_ID" IS '系统标识ID';
 
   COMMENT ON COLUMN "STOCK"."SECRET_LEVEL" IS '密级';
 
   COMMENT ON COLUMN "STOCK"."SYS_APPLICATION_ID" IS '多应用ID';
 
   COMMENT ON COLUMN "STOCK"."CREATED_BY" IS '创建人';
 
   COMMENT ON COLUMN "STOCK"."CREATION_DATE" IS '创建时间';
 
   COMMENT ON COLUMN "STOCK"."LAST_UPDATED_BY" IS '最后修改人';
 
   COMMENT ON COLUMN "STOCK"."LAST_UPDATE_DATE" IS '最后修改时间';
 
   COMMENT ON COLUMN "STOCK"."LAST_UPDATE_IP" IS '最后更新IP';
 
   COMMENT ON COLUMN "STOCK"."VERSION" IS '版本';
 
   COMMENT ON COLUMN "STOCK"."ATTRIBUTE_01" IS '预留字段1';
 
   COMMENT ON COLUMN "STOCK"."ATTRIBUTE_02" IS '预留字段2';
 
   COMMENT ON COLUMN "STOCK"."ATTRIBUTE_03" IS '预留字段3';
 
   COMMENT ON COLUMN "STOCK"."ATTRIBUTE_04" IS '预留字段4';
 
   COMMENT ON COLUMN "STOCK"."ATTRIBUTE_05" IS '预留字段5';
 
   COMMENT ON COLUMN "STOCK"."ATTRIBUTE_06" IS '预留字段6';
 
   COMMENT ON COLUMN "STOCK"."ATTRIBUTE_07" IS '预留字段7';
 
   COMMENT ON COLUMN "STOCK"."ATTRIBUTE_08" IS '预留字段8';
 
   COMMENT ON COLUMN "STOCK"."ATTRIBUTE_09" IS '预留字段9';
 
   COMMENT ON COLUMN "STOCK"."ATTRIBUTE_10" IS '预留字段10';
 
   COMMENT ON COLUMN "STOCK"."ATTRIBUTE_11" IS '预留字段11';
 
   COMMENT ON COLUMN "STOCK"."ATTRIBUTE_12" IS '预留字段12';
 
   COMMENT ON COLUMN "STOCK"."ATTRIBUTE_13" IS '预留字段13';
 
   COMMENT ON COLUMN "STOCK"."ATTRIBUTE_14" IS '预留字段14';
 
   COMMENT ON COLUMN "STOCK"."ATTRIBUTE_15" IS '预留字段15';
 
   COMMENT ON TABLE "STOCK"  IS '库存信息';
 