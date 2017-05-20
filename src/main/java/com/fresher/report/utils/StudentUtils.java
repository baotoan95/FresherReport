///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.fresher.report.utils;
//
//import java.util.List;
//
//import com.sam.fresher.report.entity.PointModel;
//import com.sam.fresher.report.entity.Student;
//
//
//
///**
// *
// * @author tuancat
// */
//public class StudentUtils {
//
//    public static List<Student> countStars(List<Student> listStudent) {
//        for (Student s : listStudent) {
//            Float avg = Float.MIN_NORMAL;
//            Float sum = Float.MIN_NORMAL;
//            int stars = 0;
//            for (PointModel p : s.getListPointModels()) {
//                if (p.getValue() != null) {
//                    sum += p.getValue();
//                }
//            }
//            avg = sum / s.getListPointModels().size();
//            if (avg < 5 ) {
//                stars = 1;
//            } else if (avg >= 5 || avg <= 7) {
//                stars = 3;
//            } else if (avg >= 7 || avg <= 9) {
//                stars = 4;
//            } else {
//                stars = 5;
//            }
//            s.setAverage(avg);
//            s.setStars(stars);
//        }
//        return listStudent;
//    }
//    
//    public static Student countStars(Student s) {
//            Float avg = Float.MIN_NORMAL;
//            Float sum = Float.MIN_NORMAL;
//            int stars = 0;
//            for (PointModel p : s.getListPointModels()) {
//                if (p.getValue() != null) {
//                    sum += p.getValue();
//                }
//            }
//            avg = sum / s.getListPointModels().size();
//            if (avg < 5 ) {
//                stars = 1;
//            } else if (avg >= 5 || avg <= 7) {
//                stars = 3;
//            } else if (avg >= 7 || avg <= 9) {
//                stars = 4;
//            } else {
//                stars = 5;
//            }
//            s.setAverage(avg);
//            s.setStars(stars);
//        return s;
//    }
//}
