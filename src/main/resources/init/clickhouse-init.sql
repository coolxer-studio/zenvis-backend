-- 测试01
CREATE TABLE IF NOT EXISTS zenvis.msg (
  user_id String,
  guid String NOT NULL,
  start_id UInt64 NOT NULL,
  sdk_version String,
  app_id UInt16,
  app_name String,
  app_package String,
  app_version String,
  platform String,
  manufacturer String,
  model String,
  system_name String,
  system_version String,
  net_type String,
  lan_ip IPv4,
  wan_ip IPv4,
  latitude Float64,
  longitude Float64,
  country String,
  province String,
  city String,
  county String,
  thoroughfare String,
  client_time DateTime,
  server_time DateTime,
  rule String,
  fact_type String NOT NULL,
  fact JSON,
  agenda_tags Array(String),
  agendas Array(String),
  punish_types Array(UInt8),
  punishes Array(String),
  insert_time DateTime DEFAULT now(),
  CONSTRAINT guid_startId_factType_not_empty CHECK guid != '' and start_id > 0 and fact_type != ''
) ENGINE = MergeTree()
ORDER BY
  (
    guid, start_id, client_time, fact_type
  );








