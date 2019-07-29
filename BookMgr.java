package 图书管理系统;

import java.lang.reflect.Array;
import java.util.Scanner;

public class BookMgr {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		//数据初始化，最多6本书
		//图书：图书名称	图书借阅状态	图书借阅日期	图书借阅次数
		String[] names = new String[6];
		int[] states = new int[6];	//0可借	1已借出
		int[] dates = new int[6];
		int[]counts = new int[6];
		
		names[0] = "白雪公主";
		dates[0] = 5;
		states[0] = 1;
		counts[0] = 10;
		
		names[1] = "mybook";
		dates[1] = 0;
		states[1] = 0;
		counts[1] = 16;
		
		names[2] = "舒克贝塔";
		dates[2] = 0;
		states[2] = 0;
		counts[2] = 78;
		
		//用户是否退出系统
		boolean flag = true;
		//用户输入0返回主菜单
		int num = 0;
		//图书管理相关操作：查看图书信息	新增图书	借阅图书	归还图书	删除图书	退出系统
		do 
		{
			System.out.println("**********欢迎使用迷你图书管理系统**********");
			System.out.println("1.查看图书");
			System.out.println("2.新增图书");
			System.out.println("3.借阅图书");
			System.out.println("4.归还图书");
			System.out.println("5.删除图书");
			System.out.println("6.退出系统");
			System.out.print("请选择您的操作：");
			int choose = in.nextInt();
			switch ( choose )
			{
				case 1:
					//查看图书
					System.out.println("\n图书信息列表-->");
					System.out.println("图书序号\t图书名称\t图书状态\t借阅日期\t借阅次数");
					for ( int i=0; i<names.length; i++ )
					{
						if( names[i] != null )
						{
							String state = ( states[i] == 0 ) ? "可借阅" : "已借出";
							String date = ( dates[i] == 0 ) ? "" : dates[i]+"日";
							String count = counts[i] + "次";
							System.out.println( ( i+1 ) + "\t" + names[i] + "\t" + state + "\t" +  date + "\t" + count );
						}
						else
						{
							//遇到第一个为null的图书名称，意味着后面的图书名称也为null,不必要循环了
							break;
						}
					}
					break;
				case 2:
					//新增图书
					System.out.println("\n新增图书-->");
					System.out.println("请输入新增图书名称:");
					String name = in.next();
					//是否能新增图书，如果货架满了（6），则无法添加false,反之，则能添加true
					boolean flagAdd = false;
					for ( int i=0; i<names.length; i++ )
					{
						if ( names[i] == null )
						{
							flagAdd = true ;
							names[i] = name;
							System.out.println("图书《"+name+"》添加成功");
							break;
						}
					}
					if ( !flagAdd )
					{
						System.out.println("对不起，货架已满，无法添加图书！");
					}
					break;
				case 3:
					//借阅图书
					System.out.println("\n借阅图书图书-->");
					System.out.println("请输入借阅图书的名称:");
					String want = in.next();
					for ( int i=0; i<names.length; i++ )
					{
						if ( names[i] == null )
						{
							//没有找到你要的书
							System.out.println("没有找到您要借的书！");
							break;
						}
						else if ( names[i].equals(want) && states[i] == 0 )
						{
							//找到了您要借的书，书的状态是可借的，可以借
							System.out.println("请输入借阅日期：");
							dates[i] = in.nextInt();
							while ( dates[i]<1 || dates[i]>31 )
							{
								System.out.println("数字格式不正确，请输入1-31之间的数字：");
								dates[i] = in.nextInt();
							}
							System.out.println("借出《"+want+"》成功！");
							states[i] = 1;	//将书的状态修改为已借出
							counts[i]++;	//将书的借阅次数累加
							break;
						}
						else if ( names[i].equals(want) && states[i] == 1 )
						{
							//找到了您要借的书，书的状态是已借出的，不可以借
							System.out.println("该书已经借出去了！");
							break;
						}
					}
					break;
				case 4:
					//归还图书
					System.out.println("\n归还图书-->");
					System.out.println("请输入归还图书名称-->");
					String returnBook = in.next();
					for ( int i=0; i<names.length; i++ )
					{
						if ( names[i] == null )
						{
							//没有找到你要的书
							System.out.println("这不是我们的书不用归还");
							break;
						}
						else if ( names[i].equals( returnBook ) && states[i] == 1 )
						{
							System.out.println("请输入归还日期:");
							int date = in.nextInt();
							while ( date<1 || date>31 || date<dates[i] )
							{
								if ( date<1 || date>31 )
								{
									System.out.println("数字格式不正确，请输入1-31之间的数字:");
									date = in.nextInt();
								}
								else if ( date<dates[i] )
								{
									System.out.println("归还日期不能小于借阅日期，请重新输入:");
									date = in.nextInt();
								}
								
							}
							
							System.out.println("归还《"+returnBook+"》成功！");
							states[i] = 0;	//重新设置借阅状态
							//计算租金
							int rent = ( date-dates[i] )*1;
							System.out.println("应付租金（元）:"+rent);
							dates[i] = 0;	//重新设置借阅日期
							break;
						}
						else if ( names[i].equals( returnBook ) && states[i] == 0 )
						{
							//找到了您要借的书，书的状态是已借出的，不可以借
							System.out.println("该书尚未借出，无需归还！");
							break;
						}
					}
					break;
				case 5:
					//删除图书
					System.out.println("\n删除图书-->");
					System.out.println("请输入删除图书名称-->");
					String deleteBook = in.next();
					//要删除图书的位置
					int index = -1;
					for ( int i=0; i<names.length; i++ )
					{
						if ( names[i] == null )
						{
							//没找到要删除的书
							System.out.println("没找到你要删除的书，删不了");
							break;
						}
						else if ( names[i].equals(deleteBook) && states[i] == 1 )
						{
							//找到了你要删的书，但是该书已经借出去了
							System.out.println("该书已经借出去了，无法删除！");
							break;
						}
						else if ( names[i].equals(deleteBook) && states[i] == 0 )
						{
							//找到了要删除的书，并且没借出去
							//记录该书的位置
							index = i;
							break;
						}
					}
					//index可能依然是-1，也可能是一个正常的位置
					//根据图书的位置进行删除，后续的图书位置依次往前覆盖
					if ( index != -1 ) 
					{
						//从index-数组的最后一本书一次往前覆盖
						for ( int i=index; i<names.length; i++ )
						{
							if ( i != names.length-1)
							{
								names[i] = names[i+1];
								counts[i] = counts[i+1];
								dates[i] = dates[i+1];
								states[i] = states[i+1];
							}
							//将最后一个元素置空
							names[names.length-1] = null;
							counts[names.length-1] = 0;
							dates[names.length-1] = 0;
							states[names.length-1] = 0;
						}
						System.out.println("删除成功");
					}
					
					break;
				case 6:
					//跳出循环
					flag = false;
					break;
				default:
					//输入了错误数字
					flag = false;
					break;
						
			}
			//如果flag == false,则结束循环，跳出系统
			if ( !flag )
			{
				//跳出循环
				break;
			}
			else
			{
				//用户不结束使用系统，代表用户想返回主菜单继续操作
				System.out.println("请输入0返回");
				num = in.nextInt();
			}
			
		}
		while( num == 0 );
		System.out.println("谢谢，欢迎使用！");
		
	}

}
