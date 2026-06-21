package com.coolxer.dao.clickhouse.repository;

import com.coolxer.dao.clickhouse.entity.Msg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * clickhouse msg 业务数据
 */
public interface MsgRepository extends JpaRepository<Msg, String> {

    @Query(nativeQuery = true,
            value = "SELECT a.* FROM msg a WHERE a.type=?1 ORDER BY a.id ASC"
            ,
            countQuery = "SELECT count(*) FROM msg a WHERE a.type=?1"
    )
    Page<Msg> findByPageByType(Pageable pageable, String type);


    @Query(nativeQuery = true,
            value = "SELECT a.* FROM msg a WHERE a.id=?1 ORDER BY a.id ASC"
            ,
            countQuery = "SELECT count(*) FROM msg a WHERE a.id=?1"
    )
    Page<Msg> findByPage(Pageable pageable, String id);


    @Query(nativeQuery = true, value = "select count(*) as y_axis_value, province as x_axis, city as y_axis_name from msg where server_time>?1 and server_time<?2 group by province, city")
    List<Map<String, Object>> countOfProvinceCity(Date startTime, Date endTime);

    @Query(nativeQuery = true, value = "select count(*) as y_axis_value, manufacturer as x_axis, concat(system_name, '-', system_version) as y_axis_name from msg where server_time>?1 and server_time<?2 group by manufacturer, system_name, system_version")
    List<Map<String, Object>> countOfManufactureSystem(Date startTime, Date endTime);


    @Query(nativeQuery = true, value = "select count(*) as msg_count, platform as group_key, toHour(server_time) as time from msg where server_time>?1 and server_time<?2 group by platform ,time order by platform ,time")
    List<Map<String, Object>> countByHour(Date startTime, Date endTime);

    @Query(nativeQuery = true, value = "select count(*) as msg_count, platform as group_key, toDate(server_time) as time from msg where server_time>?1 and server_time<?2 group by platform ,time order by platform ,time")
    List<Map<String, Object>> countByDay(Date startTime, Date endTime);

    @Query(nativeQuery = true,
            value = "SELECT user_id,guid,start_id,sdk_version,app_id,app_name,app_package,app_version,platform,manufacturer,model,system_name,system_version,net_type,lan_ip,wan_ip,latitude,longitude,country,province,city,county,thoroughfare,client_time,server_time,fact_type,arrayStringConcat(agenda_tags,',') as agenda_tags,arrayStringConcat(punish_types,',') as punish_types,toJSONString(fact) as data FROM msg WHERE guid=?1 and server_time>?2 and server_time<?3 ORDER BY server_time DESC"
            ,
            countQuery = "SELECT count(*) FROM msg WHERE guid=?1 and server_time>?2 and server_time<?3"
    )
    Page<Map<String, Object>> findByPage(Pageable pageable, String deviceId, Date startTime, Date endTime);

    @Query(nativeQuery = true, value = "select arrayStringConcat(groupArray(arrayStringConcat(agenda_tags,',')),',') as agenda_tags_array from msg where guid =?1 and server_time>?2 and server_time<?3 and length(agenda_tags) > 0")
    Map<String, Object> findAgendaTagsByDeviceId(String deviceId, Date startTime, Date endTime);

    @Query(nativeQuery = true, value = "select arrayStringConcat(groupArray(arrayStringConcat(agenda_tags,',')),',') as agenda_tags_array from msg where guid =?1 and start_id =?2 and length(agenda_tags) > 0")
    Map<String, Object> findAgendaTagsByStartId(String deviceId, Long startId);

    @Query(nativeQuery = true, value = "select avg(minus(server_time,client_time)) as receive_interval, avg(minus(insert_time,server_time)) as process_interval, count(*) as msg_count from msg where server_time > date_sub(minute,1,now()) ")
    Map<String, Object> findLastMinuteMetrics();

    @Query(nativeQuery = true, value = "select avg(minus(server_time,client_time)) as receive_interval, avg(minus(insert_time,server_time)) as process_interval, count(*) as msg_count from msg where server_time > date_sub(day,7,now()) ")
    Map<String, Object> findLastWeekMetrics();

    @Query(nativeQuery = true, value = "SELECT guid,platform,client_time FROM msg WHERE fact_type = 'StartData' ORDER BY server_time DESC LIMIT ?1")
    List<Map<String, Object>> findTopByStart(int top);

    @Query(value = "SELECT COUNT(DISTINCT start_id) FROM msg", nativeQuery = true)
    long countByStart();

    @Query(value = "SELECT COUNT(DISTINCT guid) FROM msg", nativeQuery = true)
    long countByGuid();

}
