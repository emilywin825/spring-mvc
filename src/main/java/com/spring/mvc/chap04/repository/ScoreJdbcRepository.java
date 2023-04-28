package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Score;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("jdbc")
public class ScoreJdbcRepository implements ScoreRepository{


    String url = "jdbc:mariadb://localhost:3307/spring";
    String userName="root";
    String userPassword="1234";
    Connection conn = null;
    public ScoreJdbcRepository(){

        //드라이버 클래스를 로딩 (mariadb 커넥터 로딩)
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            }
            catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
  }

    @Override
    public List<Score> findAll() {
        return null;
    }

    @Override
    public List<Score> findAll(String sort) {
        List<Score> scoreList=new ArrayList<>();
        try {
            conn = DriverManager.getConnection(url, userName, userPassword);
            conn.setAutoCommit(false);

            String sql="SELECT * from tbl_score";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                scoreList.add(new Score(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scoreList;
    }

    @Override
    public boolean save(Score score) {
        try (
            Connection conn = DriverManager.getConnection(url, userName, userPassword)) {
            conn.setAutoCommit(false);

            String sql = "INSERT INTO tbl_score " +
                    " (stu_name, kor, eng, math, total, average, grade) " +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, score.getStuName());
            pstmt.setInt(2, score.getKor());
            pstmt.setInt(3, score.getEng());
            pstmt.setInt(4, score.getMath());
            pstmt.setInt(5, score.getTotal());
            pstmt.setDouble(6, score.getAverage());
            pstmt.setString(7, String.valueOf(score.getGrade()));

            int result = pstmt.executeUpdate(); // 성공시 1, 실패시 0

            if (result == 1) {
                conn.commit();
                return true;
            }
            conn.rollback();
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean deleteByStuNum(int stuNum) {
        try (
            Connection conn = DriverManager.getConnection(url, userName, userPassword)) {
            conn.setAutoCommit(false);

            String sql = "DELETE FROM tbl_score WHERE stu_num=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,stuNum);

            int result = pstmt.executeUpdate(); // 성공시 1, 실패시 0

            if (result == 1) {
                conn.commit();
                return true;
            }
            conn.rollback();
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Score findByStuNum(int stuNum) {
        try (
                Connection conn = DriverManager.getConnection(url, userName, userPassword)) {
            conn.setAutoCommit(false);

            String sql = "SELECT * FROM tbl_score WHERE stu_num=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, stuNum);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Score(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

}
