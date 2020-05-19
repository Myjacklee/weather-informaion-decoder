package information;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class app {
	public static String dirName="src/地面资料";
	public static String name=new String();
	public String year=new String();
	public String month=new String();
	public String day=new String();
	public String time=new String();
	public String stationNumber=new String();
	
	//输出信息
	public char iR;
	public String iRMessage=new String();
	public char iX;
	public String iXMessage=new String();
	public char h;
	public String lowestCloudHeightString="暂无数据";
	public int vv;
	public String effectiveVisibilityString="暂无数据";
	public char N;
	public String totalCloudString="暂无数据";
	public String dd=new String();
	public String cloudDirection="暂无数据";
	public String ff=new String();
	public String cloudSpeedString="暂无数据";
	public String temperatureString="暂无数据";
	public String dewPoint="暂无数据";
	public String localPressure="暂无数据";
	public String seaPressure="暂无数据";
	public String pressureChangeString="暂无数据";
	public String pressureChangeDesString="暂无数据";
	public String precipitation="暂无数据";
	public String weatherPhenomena="暂无数据";
	public String cloudiness ="暂无数据";
	public String CLCloud="暂无数据";
	public String CMCloud="暂无数据";
	public String CHCloud="暂无数据";
	public String time_h=new String();
	public String time_m=new String();
	public String nowTime="暂无数据";

	//返回搜索的文件名称
	public String getFileName(){
		Scanner input=new Scanner(System.in);
		System.out.println("请输入年份");
		year=input.nextLine();
		System.out.println("请输入月份");
		month=input.nextLine();
		System.out.println("请输入日期");
		day=input.nextLine();
		System.out.println("请输入时次");
		time=input.nextLine();
		System.out.println("请输入台站号");
		stationNumber=input.nextLine();
		input.close();
		if(day.length()<2){
			day="0"+day;
		}
		if(time.length()<2){
			time="0"+time;
		}
		if(month.length()<2)
		{
			month="0"+month;
		}
		return "AAXX"+month+day+".T"+time;
	}
	//比对对应的文件名称是否一致
	public boolean FileName(String name){
		boolean isExistence=false;
		File thisDir=new File(dirName);
		if(thisDir.isDirectory()){
			String Dir[]=thisDir.list();
			for(int i=0;i<Dir.length;i++){
				if(Dir[i].equals(name)){
					isExistence=true;
				}else{
					continue;
				}
				
			}
		}
		return isExistence;
	}
	//在对应文件里面找到对应的台站号
	public String findStationNumber() throws IOException{
		String fileRoute=dirName+"/"+name;
		@SuppressWarnings("resource")
		BufferedReader in = new BufferedReader(new FileReader(fileRoute));
		String line=new String();
		while((line=in.readLine())!=null){
			if(line.indexOf("AAXX")!=-1){
				line=in.readLine();
				while(line!=null){
					String codeString=new String();
					do {
						codeString=codeString+" "+line;
						line=in.readLine();
					} while (codeString.charAt(codeString.length()-1)!='=');
				//	System.out.println(codeString);
					if(codeString.substring(1,6).equals(stationNumber)){
						return codeString.substring(1,codeString.length()-1);
						
					}
				}
			}
		}
		in.close();
		return "notExist";
	}
	//对编码进行解码
	public void decode(String code){
		String[] codeVector=code.split(" ");
		for(int i=0;i<codeVector.length-1;i++){
	//		System.out.println(codeVector[i]+" "+codeVector[i].length());
			if(codeVector[i].equals("333")||codeVector[i].equals("333//")){
				break;
			}
			if(i<3){
				if(i==1){
					String tempString=codeVector[1];
					iR=tempString.charAt(0);
					if(iR=='1'){
						iRMessage="编报降水组";
					}else if(iR=='3'){
						iRMessage="无降水而不编报";
					}else if(iR=='4'){
						iRMessage="有降水但因未观测或观测值无法测定而不编报";
					}else{
						iRMessage="无相关信息";
					}
					iX=tempString.charAt(1);
					if(iX=='1'){
						iXMessage="测站类别：人工站 编报与否：编报";
					}else if(iX=='2'){
						iXMessage="测站类别：人工站 编报与否：不编报（无规定要编报的天气现象）";
					}else if(iX=='3'){
						iXMessage="测站类别：人工站 编报与否：不编报（未观测）";
					}else if(iX=='4'){
						iXMessage="测站类别：自动站 编报与否：编报";
					}else if(iX=='5'){
						iXMessage="测站类别：自动站 编报与否：不编报（无规定要编报的天气现象）";
					}else if(iX=='6'){
						iXMessage="观站类别：自动站 编报与否：不编报（未观测）";
					}else{
						iXMessage="无相关信息";
					}
					h=tempString.charAt(2);
					if(h=='0'){
						lowestCloudHeightString="<50";
					}else if(h=='1'){
						lowestCloudHeightString="50-<100";
					}else if(h=='2'){
						lowestCloudHeightString="100-<200";
						lowestCloudHeightString="200-<300";
					}else if(h=='4'){
						lowestCloudHeightString="300-<600";
					}else if(h=='5'){
						lowestCloudHeightString="600-<1000";
					}else if(h=='6'){
						lowestCloudHeightString="1000-<1500";
					}else if(h=='7'){
						lowestCloudHeightString="1500-<2000";
					}else if(h=='8'){
						lowestCloudHeightString="2000-<2500";
					}else if(h=='9'){
						lowestCloudHeightString=">=2500米，或无云";
					}else if(h=='/'){
						lowestCloudHeightString="云底高度不明，或者云底低于测站而云顶高于测站";
					}else{
						lowestCloudHeightString="无相关信息";
					}
					vv=Integer.valueOf(tempString.substring(3,5));
					if(vv==00){
						effectiveVisibilityString="<0.1";
					}else if(vv>=1&&vv<=50){
						effectiveVisibilityString=String.valueOf(vv/10);
					}else if(vv>=51&&vv<=55){
						effectiveVisibilityString="不用";
					}else if(vv>=56&&vv<=79){
						effectiveVisibilityString=String.valueOf(vv-50);
					}else if(vv==80){
						effectiveVisibilityString=">=30";
					}else if(vv>=81&&vv<=88){
						effectiveVisibilityString=String.valueOf((vv-80)*5+30);
					}else if(vv==89){
						effectiveVisibilityString=">70";
					}else if(vv==90){
						effectiveVisibilityString="<=0.05";
					}else if(vv==92){
						effectiveVisibilityString="0.05";
					}else if(vv==93){
						effectiveVisibilityString="0.5";
					}else if(vv==94){
						effectiveVisibilityString="1";
					}else if(vv==95){
						effectiveVisibilityString="2";
					}else if(vv==96){
						effectiveVisibilityString="4";
					}else if(vv==97){
						effectiveVisibilityString="10";
					}else if(vv==98){
						effectiveVisibilityString="20";
					}else if(vv==99){
						effectiveVisibilityString=">=50";
					}else{
						effectiveVisibilityString="无相关信息";
					}
				}else if(i==2){
					String tempString=codeVector[2];
					N=tempString.charAt(0);
					if(N=='0'){
						totalCloudString="无云";
					}else if(N=='1'){
						totalCloudString="1或微量（可判定云状的微量云）";
					}else if(N=='2'){
						totalCloudString="2-3";
					}else if(N=='3'){
						totalCloudString="4";
					}else if(N=='4'){
						totalCloudString="5";
					}else if(N=='5'){
						totalCloudString="6";
					}else if(N=='6'){
						totalCloudString="7-8";
					}else if(N=='7'){
						totalCloudString="9或10";
					}else if(N=='8'){
						totalCloudString="10";
					}else if(N=='9'){
						totalCloudString="因有雾或其他视程障碍现象而使总云量无法估计";
					}else if(N=='/'){
						totalCloudString="未观测(自动站未配有测云设备）";
					}else{
						totalCloudString="无相关信息";
					}
					dd=tempString.substring(1,3);
					if(dd.equals("02")){
						cloudDirection="东北风（NNE）";
					}else if(dd.equals("04")){
						cloudDirection="东北（NE）";
					}else if(dd.equals("07")){
						cloudDirection="东东北（ENE）";
					}else if(dd.equals("09")){
						cloudDirection="东 （E）";
					}else if(dd.equals("11")){
						cloudDirection="东东南（ESE）";
					}else if(dd.equals("14")){
						cloudDirection="东 南（SE）";
					}else if(dd.equals("16")){
						cloudDirection="南东南（SSE）";
					}else if(dd.equals("18")){
						cloudDirection="南 （S）";
					}else if(dd.equals("20")){
						cloudDirection="南西南（SSW）";
					}else if(dd.equals("22")){
						cloudDirection="西南（SW）";
					}else if(dd.equals("25")){
						cloudDirection="西西南（WSW）";
					}else if(dd.equals("27")){
						cloudDirection="西（W）";
					}else if(dd.contentEquals("29")){
						cloudDirection="西西北（WNW）";
					}else if(dd.equals("32")){
						cloudDirection="西北（NW）";
					}else if(dd.equals("34")){
						cloudDirection="北西北（NNW）";
					}else if(dd.equals("36")){
						cloudDirection="北（N）";
					}else if(dd.equals("00")){
						cloudDirection="静风";
					}else{
						cloudDirection="无相关信息";
					}
					ff=tempString.substring(3,5);
					if(ff.charAt(0)=='0'){
						cloudSpeedString=ff.substring(1);
					}else{
						cloudSpeedString=ff;
					}
				}
			}else{
				String tempString=codeVector[i];
				char beginChar=tempString.charAt(0);
				if(beginChar=='1'){
					if(tempString.charAt(1)=='0'){
						String temp=tempString.substring(2,4);
						if(temp.charAt(0)=='0'){
							temp=temp.substring(1);
						}
						temperatureString=temp+"."+tempString.substring(4);
					}else{
						String temp=tempString.substring(2,4);
						if(temp.charAt(0)=='0'){
							temp=temp.substring(1);
						}
						temperatureString="-"+temp+"."+tempString.substring(4);
					}
					
				}else if(beginChar=='2'){
					if(tempString.charAt(1)=='9'){
						if(tempString.charAt(2)=='1'){
							dewPoint="100%";
						}else{
							dewPoint=tempString.substring(3,5)+"%";
						}
					}else{
						if(tempString.substring(2,5)=="800"){
							dewPoint="低于-80.0 C";
						}else{
							String temp=tempString.substring(2,4);
							if(temp.charAt(0)=='0'){
								temp=temp.substring(1);
							}
							if(tempString.charAt(1)=='0'){

								dewPoint=temp+"."+tempString.substring(4);
							}else{
								dewPoint="-"+temp+"."+tempString.substring(4);
							}						
						}						
					}
				}else if(beginChar=='3'){
					String temp=tempString.substring(1,4);
					int tempLength=temp.length();
					for(int innerI=0;innerI<tempLength;)
					{
						if(temp.charAt(innerI)=='0'){
							temp=temp.substring(innerI+1);
							continue;
						}else{
							break;
						}
					}
					localPressure=temp+"."+tempString.substring(4);
				}else if(beginChar=='4'){
					seaPressure="1"+tempString.substring(1,4)+"."+tempString.substring(4);
				}else if(beginChar=='5'){
					if(tempString.indexOf('/')!=-1){
						pressureChangeString="暂无数据";
					}else if(tempString.charAt(1)=='2'){
						pressureChangeDesString="上升";
						String temp=tempString.substring(2,4);
						if(temp.charAt(0)=='0'){
							temp=temp.substring(1);
						}
						pressureChangeString=temp+"."+tempString.charAt(4);
					}else if(tempString.charAt(1)=='4'){
						pressureChangeDesString="气压无变量";
						pressureChangeString="0";
					}else if(tempString.charAt(1)=='7'){
						pressureChangeDesString="下降";
						String temp=tempString.substring(2,4);
						if(temp.charAt(0)=='0'){
							temp=temp.substring(1);
						}
						pressureChangeString="-"+temp+"."+tempString.charAt(4);
					}else{
						pressureChangeString="编码段错误";
					}
				}else if(beginChar=='6'){
					int temp=Integer.valueOf(tempString.substring(1,4));
					if(temp==0){
						precipitation="不用";
					}else if(temp>=1&&temp<=988){
						precipitation=String.valueOf(temp);
					}else if(temp==990){
						precipitation="微量";
					}else if(temp>=991&&temp<=999){
						precipitation=String.valueOf((temp-990)/10);
					}
				}else if(beginChar=='7'){
					String ww=tempString.substring(1,3);
					if(ww.equals("00")||ww.equals("01")){
						weatherPhenomena="过去一小时内没有出现规定要编报ww的各种天气现象";
					}else if(ww.equals("02")||ww.equals("03")){
						weatherPhenomena="不用";
					}else if(ww.equals("04")){
						weatherPhenomena="观测时水平能见度因烟（草原或森林着火而引起的烟，工厂排出的烟）或火山爆发的灰尘障碍而降低";
					}else if(ww.equals("05")){
						weatherPhenomena="观测时有霾";
					}else if(ww.equals("06")){
						weatherPhenomena="观测时有浮尘，广泛散布的浮在空中的尘土，不是在观测时由测站或测站附近的风所吹起来的。";
					}else if(ww.equals("07")){
						weatherPhenomena="观测时由测站或测站附近的风吹起来的扬沙或尘土，但还没有发展成完好的尘卷风或沙尘暴；或飞沫吹到观测船上";
					}else if(ww.equals("08")){
						weatherPhenomena="观测时或观测前一小时内在测站或测站附近看到发展完好的尘卷风，但没有沙尘暴";
					}else if(ww.equals("09")){
						weatherPhenomena="观测时视区内有沙尘暴，或者观测前一小时内测站有沙尘暴";
					}else if(ww.equals("10")){
						weatherPhenomena="轻雾";
					}else if(ww.equals("11")){
						weatherPhenomena="测站有浅雾，呈片状，在陆地上厚度不超过2米，在海上不超过10米";
					}else if(ww.equals("12")){
						weatherPhenomena="测站有浅雾，基本连续，在陆地上厚度不超过2米，在海上不超过10米";
					}else if(ww.equals("13")){
						weatherPhenomena="闪电";
					}else if(ww.equals("14")){
						weatherPhenomena="视区内有降水，没有到达地面或海面";
					}else if(ww.equals("15")){
						weatherPhenomena="视区内有降水，已经到达地面或海面，但估计距测站5千米以外";
					}else if(ww.equals("16")){
						weatherPhenomena="视区内有降水，已经到达地面或海面，在测站附近，但本站无降水";
					}else if(ww.equals("17")){
						weatherPhenomena="雷暴，但观测时测站没有降水";
					}else if(ww.equals("18")){
						weatherPhenomena="飑，观测时或观测前一小时内在测站或视区内出现";
					}else if(ww.equals("19")){
						weatherPhenomena="龙卷，观测时或观测前一小时内在测站或视区内出现";
					}else if(ww.equals("20")){
						weatherPhenomena="毛毛雨";
					}else if(ww.equals("21")){
						weatherPhenomena="雨";
					}else if(ww.equals("22")){
						weatherPhenomena="非阵性的雪、米雪或冰粒";
					}else if(ww.equals("23")){
						weatherPhenomena="雨夹雪，或雨夹冰粒";
					}else if(ww.equals("24")){
						weatherPhenomena="毛毛雨或雨，并有雨凇结成";
					}else if(ww.equals("25")){
						weatherPhenomena="阵雨";
					}else if(ww.equals("26")){
						weatherPhenomena="阵雪，或阵性雨夹雪";
					}else if(ww.equals("27")){
						weatherPhenomena="冰雹或霰（伴有或不伴有雨） ";
					}else if(ww.equals("28")){
						weatherPhenomena="雾";
					}else if(ww.equals("29")){
						weatherPhenomena="雷暴（伴有或不伴有降水）";
					}else if(ww.equals("30")){
						weatherPhenomena="轻的或中度的沙尘暴，过去一小时内减弱";
					}else if(ww.equals("31")){
						weatherPhenomena="轻的或中度的沙尘暴，过去一小时内没有显著的变化";
					}else if(ww.equals("32")){
						weatherPhenomena="轻的或中度的沙尘暴，过去一小时内开始或增强";
					}else if(ww.equals("33")){
						weatherPhenomena="强的沙尘暴，过去一小时内减弱";
					}else if(ww.equals("34")){
						weatherPhenomena="强的沙尘暴，过去一小时内没有显著的变化";
					}else if(ww.equals("35")){
						weatherPhenomena="强的沙尘暴，过去一小时内开始或增强";
					}else if(ww.equals("36")){
						weatherPhenomena="轻的或中度的低吹雪，吹雪所达高度一般低于观测员的眼睛（水平视线）";
					}else if(ww.equals("37")){
						weatherPhenomena="强的低吹雪，吹雪所达高度一般低于观测员的眼睛（水平视线）";
					}else if(ww.equals("38")){
						weatherPhenomena="轻的或中度的高吹雪，吹雪所达高度一般高于观测员的眼睛（水平视线）";
					}else if(ww.equals("39")){
						weatherPhenomena="强的高吹雪，吹雪所达高度一般高于观测员的眼睛（水平视线），或雪暴";
					}else if(ww.equals("40")){
						weatherPhenomena="观测时近处有雾，其高度高于观测员的眼睛（水平视线），但观测前一小时内测站没有雾";
					}else if(ww.equals("41")){
						weatherPhenomena="散片的雾";
					}else if(ww.equals("42")){
						weatherPhenomena="雾，过去一小时内已变薄，天空可辨明";
					}else if(ww.equals("43")){
						weatherPhenomena="雾，过去一小时内已变薄，天空不可辨";
					}else if(ww.equals("44")){
						weatherPhenomena="雾，过去一小时内强度没有显著的变化，天空可辨明";
					}else if(ww.equals("45")){
						weatherPhenomena="雾，过去一小时内强度没有显著的变化，天空不可辨";
					}else if(ww.equals("46")){
						weatherPhenomena="雾，过去一小时内开始出现或已变浓，天空可辨明";
					}else if(ww.equals("47")){
						weatherPhenomena="雾，过去一小时内开始出现或已变浓，天空不可辨";
					}else if(ww.equals("48")){
						weatherPhenomena="雾，有雾凇结成，天空可辨明";
					}else if(ww.equals("49")){
						weatherPhenomena="雾，有雾凇结成，天空不可辨";
					}else if(ww.equals("50")){
						weatherPhenomena="间歇性轻毛毛雨";
					}else if(ww.equals("51")){
						weatherPhenomena="连续性轻毛毛雨";
					}else if(ww.equals("52")){
						weatherPhenomena="间歇性中常毛毛雨";
					}else if(ww.equals("53")){
						weatherPhenomena="连续性中常毛毛雨";
					}else if(ww.equals("54")){
						weatherPhenomena="间歇性浓毛毛雨";
					}else if(ww.equals("55")){
						weatherPhenomena="连续性浓毛毛雨";
					}else if(ww.equals("56")){
						weatherPhenomena="轻的毛毛雨，并有雨凇结成";
					}else if(ww.equals("57")){
						weatherPhenomena="中常的或浓的毛毛雨，并有雨凇结成";
					}else if(ww.equals("58")){
						weatherPhenomena="轻的毛毛雨夹雨";
					}else if(ww.equals("59")){
						weatherPhenomena="中常的或浓的毛毛雨夹雨";
					}else if(ww.equals("60")){
						weatherPhenomena="间歇性小雨";
					}else if(ww.equals("61")){
						weatherPhenomena="连续性小雨";
					}else if(ww.equals("62")){
						weatherPhenomena="间歇性中雨";
					}else if(ww.equals("63")){
						weatherPhenomena="连续性中雨";
					}else if(ww.equals("64")){
						weatherPhenomena="间歇性大雨";
					}else if(ww.equals("65")){
						weatherPhenomena="连续性大雨";
					}else if(ww.equals("66")){
						weatherPhenomena="小雨，并有雨凇结成";
					}else if(ww.equals("67")){
						weatherPhenomena="中雨或大雨，并有雨凇结成";
					}else if(ww.equals("68")){
						weatherPhenomena="小的雨夹雪，或轻毛毛雨夹雪";
					}else if(ww.equals("69")){
						weatherPhenomena="中常的或大的雨夹雪，或中常的或浓的毛毛雨夹雪";
					}else if(ww.equals("70")){
						weatherPhenomena="间歇性小雪";
					}else if(ww.equals("71")){
						weatherPhenomena="连续性小雪";
					}else if(ww.equals("72")){
						weatherPhenomena="间歇性中雪";
					}else if(ww.equals("73")){
						weatherPhenomena="连续性中雪";
					}else if(ww.equals("74")){
						weatherPhenomena="间歇性大雪";
					}else if(ww.equals("75")){
						weatherPhenomena="连续性大雪";
					}else if(ww.equals("76")){
						weatherPhenomena="冰针（伴有或不伴有雾）";
					}else if(ww.equals("77")){
						weatherPhenomena="米雪（伴有或不伴有雾）";
					}else if(ww.equals("78")){
						weatherPhenomena="孤立的星状雪晶（伴有或不伴有雾）";
					}else if(ww.equals("79")){
						weatherPhenomena="冰粒";
					}else if(ww.equals("80")){
						weatherPhenomena="小的阵雨";
					}else if(ww.equals("81")){
						weatherPhenomena="中常的阵雨";
					}else if(ww.equals("82")){
						weatherPhenomena="大的阵雨";
					}else if(ww.equals("83")){
						weatherPhenomena="小的阵性雨夹雪";
					}else if(ww.equals("84")){
						weatherPhenomena="中常或大的阵性雨夹雪";
					}else if(ww.equals("85")){
						weatherPhenomena="小的阵雪";
					}else if(ww.equals("86")){
						weatherPhenomena="中常或大的阵雪";
					}else if(ww.equals("87")){
						weatherPhenomena="小的阵性霰，伴有或不伴有雨或雨夹雪";
					}else if(ww.equals("88")){
						weatherPhenomena="中常或大的阵性霰，伴有或不伴有雨或雨夹雪";
					}else if(ww.equals("89")){
						weatherPhenomena="轻的冰雹，伴有或不伴有雨或雨夹雪";
					}else if(ww.equals("90")){
						weatherPhenomena="中常或强的冰雹，伴有或不伴有雨或雨夹雪";
					}else if(ww.equals("91")){
						weatherPhenomena="观测前一小时内有雷暴，观测时有小雨";
					}else if(ww.equals("92")){
						weatherPhenomena="观测前一小时内有雷暴，观测时有中雨或大雨";
					}else if(ww.equals("93")){
						weatherPhenomena="观测前一小时内有雷暴，观测时有小（轻）的雪、或雨夹雪、或霰、或冰雹";
					}else if(ww.equals("94")){
						weatherPhenomena="观测前一小时内有雷暴，观测时有中常或大（强）的雪、或雨夹雪、或霰、或冰雹";
					}else if(ww.equals("95")){
						weatherPhenomena="小或中常的雷暴，观测时没有冰雹、或霰，但有雨、或雪、或雨夹雪";
					}else if(ww.equals("96")){
						weatherPhenomena="小或中常的雷暴，观测时伴有冰雹、或霰";
					}else if(ww.equals("97")){
						weatherPhenomena="大雷暴，观测时没有冰雹、或霰，但有雨、或雪、或雨夹雪";
					}else if(ww.equals("98")){
						weatherPhenomena="雷暴，观测时伴有沙尘暴和降水";
					}else if(ww.equals("99")){
						weatherPhenomena="大雷暴，观测时伴有冰雹、或霰";
					}else{
						weatherPhenomena="无相关信息";
					}
				}else if(beginChar=='8'){
					char temp=tempString.charAt(1);
					if(temp=='0'){
						cloudiness="无云";
					}else if(temp=='1'){
						cloudiness="1或微量（可判定云状的微量云）";
					}else if(temp=='2'){
						cloudiness="2-3";
					}else if(temp=='3'){
						cloudiness="4";
					}else if(temp=='4'){
						cloudiness="5";
					}else if(temp=='5'){
						cloudiness="6";
					}else if(temp=='6'){
						cloudiness="7-8";
					}else if(temp=='7'){
						cloudiness="9或10";
					}else if(temp=='8'){
						cloudiness="10";
					}else if(temp=='9'){
						cloudiness="因有雾或其他视程障碍现而使总云量无法估计";
					}else if(temp=='/'){
						cloudiness="未观测（自动站未装配有测云设备）";
					}else{
						cloudiness="无相关信息";
					}
					temp=tempString.charAt(2);
					if(temp=='0'){
						CLCloud="技术性说明：没有CL云 非技术性说明：没有层积云、层云、积云、积雨云";
					}else if(temp=='1'){
						CLCloud="技术性说明：淡积云或碎积云，或者两者同时存在 非技术性说明：垂直发展很小，形状扁平的积云，或碎积云，或两者同时存在。";
					}else if(temp=='2'){
						CLCloud="技术性说明：浓积云，可伴有淡积云、碎积云或层积云，云底在同一高度上。 非技术性说明：垂直发展很可观的积云，一般都呈塔状，在此云底的同一高度上可伴有别种积云或层积云。";
					}else if(temp=='3'){
						CLCloud="技术性说明：秃积雨云，可伴有积云或层积云或层云。 非技术性说明：积雨云，顶部轮廓模糊，但显然不是卷云状的，也不是砧状的；可伴有积云或层积云或层云。";
					}else if(temp=='4'){
						CLCloud="技术性说明：积云性层积云。 非技术性说明：层积云，由积云扩展而成，时常伴有积云。";
					}else if(temp=='5'){
						CLCloud="技术性说明：层积云，不是积云性的。 非技术性说明：层积云、不是由积云扩展而成。";
					}else if(temp=='6'){
						CLCloud="技术性说明：层云和（或）碎层云，但不是恶劣天气的碎雨云。 非技术性说明：层云或碎层云，或层云和碎层云两者同时存在，但不是恶劣天气下的碎雨云。";
					}else if(temp=='7'){
						CLCloud="技术性说明：恶劣天气下的碎雨云，通常在高层云或雨层云之下。 非技术性说明：恶劣天气下的碎雨云，通常位于高层云或雨层云之下（恶劣天气指降水时或降水前后一小段时间内的天气状况）。";
					}else if(temp=='8'){
						CLCloud="技术性说明：积云和不是积云性的层积云同时存在，此两种云的底部高度不同。 非技术性说明：积云和不是由积云扩展而成的层积云同时存在，两种云底部不在同一高度上。";
					}else if(temp=='9'){
						CLCloud="技术性说明：鬃积雨云，常带砧状，可伴有积云、层积云、层云或恶劣天气下的碎云。 非技术性说明：具有清晰的纤维状的（即卷云状的）顶部的积雨云，云顶常带有砧状，可伴有积云、层积云、层云或恶劣天气下的的碎云。";
					}else if(temp=='/'){
						CLCloud="由于黑暗、或雾、或沙尘暴、或其他类似现象以致看不到属于CL的各属云";
					}else{
						CLCloud="无相关信息";
					}
					temp=tempString.charAt(2);
					if(temp=='0'){
						CMCloud="技术性说明：没有CM云。 非技术性说明：没有高积云、高层云、雨层云。";
					}else if(temp=='1'){
						CMCloud="技术性说明：透光高层云 非技术性说明：薄的（半透明的）高层云、从这种云看过去，可以朦胧地看到太阳或月亮，好象隔着一层毛玻璃一样。";
					}else if(temp=='2'){
						CMCloud="技术性说明：蔽光高层云或雨层云。 非技术性说明：厚的高层云或雨层云（有时从云层的某些部分看过去，可以找到比较明亮的小块来指示出太阳或月亮的位置）。";
					}else if(temp=='3'){
						CMCloud="技术性说明：透光高积云，较稳定，并且在同一个高度上。 非技术性说明：薄的（半透明的）高积云，各个云块没有显著变化，并且在同一高度上。";
					}else if(temp=='4'){
						CMCloud="技术性说明：透光高积云（常呈荚状）或荚状层积云，连续不断地在改变中，并且出现在一个或几个高度上。 非技术性说明：一块块薄的（半透明的）高积云片或层积云片（常呈荚状）；云块连续不断地在变化中，并且出现在一个或几个高度上";
					}else if(temp=='5'){
						CMCloud="技术性说明：成带的或成层的透光高积云，有系统地侵入天空，常常全部增厚，甚至有一部分已变成蔽光高积云或复高积云。 非技术性说明：成带或成层的薄的（半透明的）高积云，迅速向天空扩展，并且全部增厚，它的一部分可能已变成不透光的或成为双层的。";
					}else if(temp=='6'){
						CMCloud="技术性说明：积云性高积云 非技术性说明：由积云或积雨云扩展而成的高积云。";
					}else if(temp=='7'){
						CMCloud="技术性说明：复高积云或蔽光高积云，不是有系统地侵盖天空；或者高层云和高积云同时存在。 非技术性说明：可能有下列情况：（1）双层高积云，通常有些部分不透明，不是有系统地侵盖天空；（2）一厚层的（不透明的）高积云；不是有系统地侵盖天空；（3）在同一高度上或在不同高度上有高层云和高积云。";
					}else if(temp=='8'){
						CMCloud="技术性说明：积云状高积云（絮状的或堡状的）或堡状层积云。 非技术性说明：呈积云形状的，一球一球的高积云或具有小塔形状的高积云或高层云。";
					}else if(temp=='9'){
						CMCloud="技术性说明：混乱天空的高积云，常出现在几个高度上。 非技术性说明：混乱天空的高积云，常出现在几个高度上。";
					}else if(temp=='/'){
						CMCloud="由于黑暗、或雾、或沙尘暴、或其他类似现象，或者完整的较低云层存在，以致看不到属于CM的各属云。";
					}else{
						CMCloud="无相关信息";
					}
					temp=tempString.charAt(3);
					if(temp=='0'){
						CHCloud="技术性说明：没有CH云 非技术说明：没有卷云、卷层云、卷积云";
					}else if(temp=='1'){
						CHCloud="技术性说明：毛卷云，分散在天空，不是有系统地侵盖天空。 非技术性说明：一丝丝的或一条条的卷云，分散在天空，不是有系统地侵盖天空（通常叫做马尾云）。;";
					}else if(temp=='2'){
						CHCloud="技术性说明：密卷云，成散片或卷曲束状，通常量不增加，有时好象是积雨云顶部的残余部分。 非技术性说明：散布的或卷曲的一束束的浓密的卷云，通常量不增加，有时好象是积雨云顶部的残余部分。;";
					}else if(temp=='3'){
						CHCloud="技术性说明：伪卷云，或为过去的积雨云的残余部分，或为远处母体看不到的积雨云的顶部。 非技术性说明：卷云，常为砧状，或者是积雨云顶部的残余部分，或为远处母体看不到的积雨云的顶部（假使对这种卷云是来自积雨云有怀疑时，那么应该报电码2）;";
					}else if(temp=='4'){
						CHCloud="技术性说明：卷云（常常是钩卷云）有系统地侵盖天空，并且常常全部增厚。 非技术性说明：卷云（常常是钩状的）渐渐地在天空中伸展，并且常常全部增厚。;";
					}else if(temp=='5'){
						CHCloud="技术性说明：辐辏状卷云和卷层云，或只有卷层云，有系统地侵盖天空，且常全部增厚，但卷层云幕前缘的高度角不到45°。 非技术性说明：卷云（常成一带带的向地平线辐合）和卷层云，或只有卷层云；总是渐渐地在天空中伸展着，并且常常全部增厚，卷层云幕前缘的高度角不到45°。;";
					}else if(temp=='6'){
						CHCloud="技术性说明：辐辏状卷云和卷层云，或只有卷层云，有系统地侵盖天空，且常全部增厚，同时卷层云幕前缘的高度角已超过45°，但未布满全天。 非技术性说明：卷云（常成一带带的向地平线辐合）和卷层云，或只有卷层云；总是渐渐地在天空中伸展着，并且常常全部增厚，同时卷层云幕前缘的高度角已超过45°，但未布满全天。;";
					}else if(temp=='7'){
						CHCloud="技术性说明：卷层云布满全天。 非技术性说明：卷层云幕遮蔽整个天空。;";
					}else if(temp=='8'){
						CHCloud="技术性说明：卷层云，不是有系统地侵盖天空，也没有布满全天。 非技术性说明：卷层云，不是有系统地侵盖天空，也没有遮蔽整个天空。;";
					}else if(temp=='9'){
						CHCloud="技术性说明： 非技术性说明：只有卷积云；或卷积云伴有卷云或（和）卷层云，但卷积云量多于其他高云。;";
					}else if(temp=='/'){
						CHCloud="由于黑暗、或雾、或沙尘暴、或其他类似现象，或者完整的较低云层存在，以致看不到属于CH的各属云。";
					}else{
						CHCloud="无相关信息";
					}
				}else if(beginChar=='9'){
					time_h=tempString.substring(1,3);
					time_m=tempString.substring(3,5);
					if(time_h.charAt(0)=='0'){
						time_h=time_h.substring(1);
					}
					if(time_m.charAt(0)=='0'){
						time_m=time_h.substring(1);
					}
					nowTime=time_h+":"+time_m;
				}
			}
		}
	}
	//输出所有可用的编码信息
	public void printAll(){
		System.out.print("日期：");
		System.out.println(year+"年 "+month+"月"+day+"日");
		System.out.print("时间：");
		System.out.println(nowTime);
		System.out.print("台站号：");
		System.out.println(stationNumber);
		System.out.print("最低云的高度：");
		System.out.println(lowestCloudHeightString);
		System.out.print("有效能见度：");
		System.out.println(effectiveVisibilityString);
		System.out.print("总云量：");
		System.out.println(totalCloudString);
		System.out.print("风向：");
		System.out.println(cloudDirection);
		System.out.print("风速：");
		System.out.println(cloudSpeedString);
		System.out.print("气温：");
		System.out.println(temperatureString);
		System.out.print("露点温度：");
		System.out.println(dewPoint);
		System.out.print("站点气压：");
		System.out.println(localPressure);
		System.out.print("海平面气压：");
		System.out.println(seaPressure);
		System.out.print("气压变化：");
		System.out.println(pressureChangeString);
		System.out.print("降水量：");
		System.out.println(precipitation);
		System.out.print("天气现象：");
		System.out.println(weatherPhenomena);
		System.out.print("低状云情况：");
		System.out.println(CLCloud);
		System.out.print("中状云情况：");
		System.out.println(CMCloud);
		System.out.print("高状云情况：");
		System.out.println(CHCloud);
		System.out.print("总云量情况：");
		System.out.println(cloudiness);
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		app newApp=new app();
		name=newApp.getFileName();
		if(newApp.FileName(name)){
			String code=newApp.findStationNumber();
			if("notExist".equals(code)){
				System.out.println("请求的台站号不存在！");
			}else{
				System.out.println("请求的台站号编码为：");
				System.out.println(code);
				newApp.decode(code);
				newApp.printAll();
			}
			
		//	newApp.decode(code);
		}
		else{
			System.out.println("未找到对应文件！");
		}
	}

}
