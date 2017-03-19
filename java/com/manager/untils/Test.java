 package com.manager.untils;

import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;

import com.manager.bean.User;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ratingsPath="D://�ȸ�����//ml-1m//ratings.dat";
		String moviesPath="D://�ȸ�����//ml-1m//movies.dat";
		int[] ranks = { 8, 12 };
		float[] lambdas = { 0.1f, 10.0f };
		int[] numIters = { 10, 20 };
		int userId=4;
		int numResult=4;
		String modelSavePath="D://�ȸ�����//ml-1m//";
		Recommend recommend=new Recommend();
		//1.�����и�����
		recommend.splitData(ratingsPath, moviesPath);
		//2.ѵ������
		recommend.trainData();
		//3.ѵ��ģ��
		MatrixFactorizationModel bestModel=recommend.trainModel(recommend.getTraining(), recommend.getValidation(), ranks, lambdas, numIters,modelSavePath);
		//4.����RMSE
		recommend.computeRMSE(bestModel, recommend.getTest());
		//5.�Ƽ����
		System.out.println("���Ϊ��"+recommend.recommendationsResult(userId, bestModel, recommend.getRatings(), recommend.getProducts(), numResult));
	}

}
