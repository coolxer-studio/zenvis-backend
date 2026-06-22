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

--测试02
  CREATE TABLE IF NOT EXISTS zenvis.msg_start(
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
    fact JSON,
    fact_types Array(String),
    agenda_tags Array(String),
    punish_types Array(String),
    insert_time DateTime DEFAULT now()
  ) ENGINE = ReplacingMergeTree(server_time)
ORDER BY
  (guid, start_id);

CREATE MATERIALIZED VIEW zenvis.msg_start_view TO zenvis.msg_start AS
SELECT
  a.user_id,
  a.guid,
  a.start_id,
  a.sdk_version,
  a.app_id,
  a.app_name,
  a.app_package,
  a.app_version,
  a.platform,
  a.manufacturer,
  a.model,
  a.system_name,
  a.system_version,
  a.net_type,
  a.lan_ip,
  a.wan_ip,
  a.latitude,
  a.longitude,
  a.country,
  a.province,
  a.city,
  a.county,
  a.thoroughfare,
  a.client_time,
  a.server_time,
  a.fact,
  b.fact_types,
  b.agenda_tags,
  b.punish_types,
  now()
FROM
  zenvis.msg as a
  left join (
    select
      start_id,
      guid,
      groupArray(fact_type) as fact_types,
      groupArrayArray(agenda_tags) as agenda_tags,
      groupArrayArray(punish_types) as punish_types
    from
      zenvis.msg
    group by
      guid,
      start_id
  ) as b on a.start_id = b.start_id
  and a.guid = b.guid
WHERE
  fact_type = 'StartData';

CREATE TABLE IF NOT EXISTS zenvis.msg_device(
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
    fact JSON,
    fact_types Array(String),
    agenda_tags Array(String),
    punish_types Array(String),
    insert_time DateTime DEFAULT now()
  ) ENGINE = ReplacingMergeTree(server_time)
ORDER BY
  (guid);

CREATE MATERIALIZED VIEW zenvis.msg_device_view TO zenvis.msg_device AS
SELECT
  a.user_id,
  a.guid,
  a.start_id,
  a.sdk_version,
  a.app_id,
  a.app_name,
  a.app_package,
  a.app_version,
  a.platform,
  a.manufacturer,
  a.model,
  a.system_name,
  a.system_version,
  a.net_type,
  a.lan_ip,
  a.wan_ip,
  a.latitude,
  a.longitude,
  a.country,
  a.province,
  a.city,
  a.county,
  a.thoroughfare,
  a.client_time,
  a.server_time,
  a.fact,
  b.fact_types,
  b.agenda_tags,
  b.punish_types,
  now()
FROM
  zenvis.msg as a
  left join (
    select
      guid,
      groupArray(fact_type) as fact_types,
      groupArrayArray(agenda_tags) as agenda_tags,
      groupArrayArray(punish_types) as punish_types
    from
      zenvis.msg
    group by
      guid
  ) as b on a.guid = b.guid
WHERE
  fact_type = 'DeviceData';








