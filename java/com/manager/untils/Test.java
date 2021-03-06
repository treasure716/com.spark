 package com.manager.untils;

import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;

import com.manager.bean.User;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ratingsPath="D://谷歌下载//ml-1m//ratings.dat";
		String moviesPath="D://谷歌下载//ml-1m//movies.dat";
		int[] ranks = { 8, 12 };
		float[] lambdas = { 0.1f, 10.0f };
		int[] numIters = { 10, 20 };
		int userId=4;
		int numResult=4;
		String modelSavePath="D://谷歌下载//ml-1m//";
		Recommend recommend=new Recommend();
		//1.导入切割数据
		recommend.splitData(ratingsPath, moviesPath);
		//2.训练数据
		recommend.trainData();
		//3.训练模型
		MatrixFactorizationModel bestModel=recommend.trainModel(recommend.getTraining(), recommend.getValidation(), ranks, lambdas, numIters,modelSavePath);
		//4.计算RMSE
		recommend.computeRMSE(bestModel, recommend.getTest());
		//5.推荐结果
		System.out.println("结果为："+recommend.recommendationsResult(userId, bestModel, recommend.getRatings(), recommend.getProducts(), numResult));
	}

}
