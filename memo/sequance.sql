
create table IF NOT EXISTS sequence (       
seq_name        VARCHAR(50) NOT NULL, -- 序列名称       
current_val     bigint(16)         NOT NULL, -- 当前值       
increment_val   int(4)         NOT NULL DEFAULT 1, -- 步长(跨度)       
PRIMARY KEY (seq_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP FUNCTION IF EXISTS `currval`;
DELIMITER //
CREATE  FUNCTION `currval`(seq_n VARCHAR(50)) RETURNS bigint(16)
    READS SQL DATA
    DETERMINISTIC
BEGIN  
DECLARE VALUE bigint;  
SET VALUE = 0;  
SELECT current_val INTO VALUE FROM sequence WHERE seq_name = seq_n;  
RETURN VALUE;  
END//
DELIMITER ;


DROP FUNCTION IF EXISTS `nextval`;  
DELIMITER //
CREATE  FUNCTION `nextval`(seq_n VARCHAR(50)) RETURNS bigint(16)
    DETERMINISTIC  
BEGIN  
UPDATE sequence SET current_val = current_val + increment_val WHERE seq_name = seq_n;  
RETURN currval(seq_n);  
END//
DELIMITER ;

INSERT INTO `sequence` (seq_name, current_val) VALUES ("ypay_phone", 10000000000);
INSERT INTO `sequence` (seq_name, current_val) VALUES ("mch_index", 1000000);

select nextval('ypay_phone');