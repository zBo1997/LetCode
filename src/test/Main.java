package test;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        int cpuCores = Runtime.getRuntime().availableProcessors(); // 获取当前系统的 CPU 核心数量
        int fixedPoolSize = cpuCores * 2; // 根据 CPU 核心数量设置固定线程池大小，这里设置为 CPU 核心数量的两倍
        // 创建一个固定大小的线程池
        ExecutorService executor = Executors.newFixedThreadPool(fixedPoolSize);
        // 提交查询任务给线程池
        Future<Void> future = executor.submit(() -> {
            try (Connection connection = DriverManager.getConnection("jdbc:sqlite:D:\\sqliteDB\\Q1.DB")) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(
                    "SELECT id, data FROM json_data_52e3cb5d23853bbea3dbe693b65345d9bb7966c1 where json_type(data) = 'object'");
                // 遍历结果集
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String data = resultSet.getString("data");
                    JSONObject jsonObject = JSONObject.parseObject(data);
                    //判断是否是json中是否有值为数组的字段
                    jsonObject.forEach((k, v) -> {
                        if (v instanceof JSONArray) {
                            //如果这歌 JSONArray 包含 null 则输出
                            JSONArray jsonArray = (JSONArray) v;
                            //优化：使用forEach方法遍历JSONArray
                            jsonArray.forEach(item -> {
                                if (item == null) {
                                    //输出id 以逗号分隔 如果结尾不输出逗号
                                    System.out.print(id + ",");
                                }
                            });
                        }
                    });
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });

        // 等待查询任务完成
        try {
            future.get(); // 阻塞直到查询完成
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // 关闭线程池
        executor.shutdown();
    }
}