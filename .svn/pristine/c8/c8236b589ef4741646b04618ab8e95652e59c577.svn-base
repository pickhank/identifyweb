set global event_scheduler=1;
drop event IF EXISTS clear_data_scheduler;
Create event clear_data_scheduler on SCHEDULE
every 1 DAY starts '2017-11-05 00:10:00'
DO update down_jiuhe_card set html = null;


set global event_scheduler=1;
drop event IF EXISTS delete_data_scheduler;
Create event delete_data_scheduler on SCHEDULE
every 1 DAY starts '2017-11-05 00:10:00'
DO delete from notify_record where create_date < DATE_SUB(NOW(),INTERVAL 7 DAY);