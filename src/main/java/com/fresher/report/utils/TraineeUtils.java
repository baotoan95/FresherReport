/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fresher.report.utils;

import java.util.List;

import com.fresher.report.entities.Point;

/**
 *
 * @author tuancat
 */
public class TraineeUtils {

    public static double computeAverage(List<Point> points) {
	double avg = Double.MIN_NORMAL;
	double sum = Double.MIN_NORMAL;

	for (Point point : points) {
	    sum += point.getValue();
	}
	avg = Math.round((sum / points.size()) * 10) / 10.0;
	return avg;
    }

    public static int countStars(double avg) {
	int stars = 0;
	if (avg < 5) {
	    stars = 1;
	} else if (avg >= 5 && avg <= 7) {
	    stars = 3;
	} else if (avg >= 7 && avg <= 9) {
	    stars = 4;
	} else {
	    stars = 5;
	}
	return stars;
    }
    
    public static String computeRank(double avg) {
	String rank = "D";
	if(avg >=9 && avg <= 10) {
	    rank = "A+";
	} else if(avg >= 8.5 && avg <= 8.9) {
	    rank = "A";
	} else if(avg >= 6.5 && avg <= 8.4) {
	    rank = "B";
	} else if(avg >= 4.9 && avg <= 6.4) {
	    rank = "C";
	}
	return rank;
    }
}
