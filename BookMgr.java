package ͼ�����ϵͳ;

import java.lang.reflect.Array;
import java.util.Scanner;

public class BookMgr {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		//���ݳ�ʼ�������6����
		//ͼ�飺ͼ������	ͼ�����״̬	ͼ���������	ͼ����Ĵ���
		String[] names = new String[6];
		int[] states = new int[6];	//0�ɽ�	1�ѽ��
		int[] dates = new int[6];
		int[]counts = new int[6];
		
		names[0] = "��ѩ����";
		dates[0] = 5;
		states[0] = 1;
		counts[0] = 10;
		
		names[1] = "mybook";
		dates[1] = 0;
		states[1] = 0;
		counts[1] = 16;
		
		names[2] = "��˱���";
		dates[2] = 0;
		states[2] = 0;
		counts[2] = 78;
		
		//�û��Ƿ��˳�ϵͳ
		boolean flag = true;
		//�û�����0�������˵�
		int num = 0;
		//ͼ�������ز������鿴ͼ����Ϣ	����ͼ��	����ͼ��	�黹ͼ��	ɾ��ͼ��	�˳�ϵͳ
		do 
		{
			System.out.println("**********��ӭʹ������ͼ�����ϵͳ**********");
			System.out.println("1.�鿴ͼ��");
			System.out.println("2.����ͼ��");
			System.out.println("3.����ͼ��");
			System.out.println("4.�黹ͼ��");
			System.out.println("5.ɾ��ͼ��");
			System.out.println("6.�˳�ϵͳ");
			System.out.print("��ѡ�����Ĳ�����");
			int choose = in.nextInt();
			switch ( choose )
			{
				case 1:
					//�鿴ͼ��
					System.out.println("\nͼ����Ϣ�б�-->");
					System.out.println("ͼ�����\tͼ������\tͼ��״̬\t��������\t���Ĵ���");
					for ( int i=0; i<names.length; i++ )
					{
						if( names[i] != null )
						{
							String state = ( states[i] == 0 ) ? "�ɽ���" : "�ѽ��";
							String date = ( dates[i] == 0 ) ? "" : dates[i]+"��";
							String count = counts[i] + "��";
							System.out.println( ( i+1 ) + "\t" + names[i] + "\t" + state + "\t" +  date + "\t" + count );
						}
						else
						{
							//������һ��Ϊnull��ͼ�����ƣ���ζ�ź����ͼ������ҲΪnull,����Ҫѭ����
							break;
						}
					}
					break;
				case 2:
					//����ͼ��
					System.out.println("\n����ͼ��-->");
					System.out.println("����������ͼ������:");
					String name = in.next();
					//�Ƿ�������ͼ�飬����������ˣ�6�������޷����false,��֮���������true
					boolean flagAdd = false;
					for ( int i=0; i<names.length; i++ )
					{
						if ( names[i] == null )
						{
							flagAdd = true ;
							names[i] = name;
							System.out.println("ͼ�顶"+name+"����ӳɹ�");
							break;
						}
					}
					if ( !flagAdd )
					{
						System.out.println("�Բ��𣬻����������޷����ͼ�飡");
					}
					break;
				case 3:
					//����ͼ��
					System.out.println("\n����ͼ��ͼ��-->");
					System.out.println("���������ͼ�������:");
					String want = in.next();
					for ( int i=0; i<names.length; i++ )
					{
						if ( names[i] == null )
						{
							//û���ҵ���Ҫ����
							System.out.println("û���ҵ���Ҫ����飡");
							break;
						}
						else if ( names[i].equals(want) && states[i] == 0 )
						{
							//�ҵ�����Ҫ����飬���״̬�ǿɽ�ģ����Խ�
							System.out.println("������������ڣ�");
							dates[i] = in.nextInt();
							while ( dates[i]<1 || dates[i]>31 )
							{
								System.out.println("���ָ�ʽ����ȷ��������1-31֮������֣�");
								dates[i] = in.nextInt();
							}
							System.out.println("�����"+want+"���ɹ���");
							states[i] = 1;	//�����״̬�޸�Ϊ�ѽ��
							counts[i]++;	//����Ľ��Ĵ����ۼ�
							break;
						}
						else if ( names[i].equals(want) && states[i] == 1 )
						{
							//�ҵ�����Ҫ����飬���״̬���ѽ���ģ������Խ�
							System.out.println("�����Ѿ����ȥ�ˣ�");
							break;
						}
					}
					break;
				case 4:
					//�黹ͼ��
					System.out.println("\n�黹ͼ��-->");
					System.out.println("������黹ͼ������-->");
					String returnBook = in.next();
					for ( int i=0; i<names.length; i++ )
					{
						if ( names[i] == null )
						{
							//û���ҵ���Ҫ����
							System.out.println("�ⲻ�����ǵ��鲻�ù黹");
							break;
						}
						else if ( names[i].equals( returnBook ) && states[i] == 1 )
						{
							System.out.println("������黹����:");
							int date = in.nextInt();
							while ( date<1 || date>31 || date<dates[i] )
							{
								if ( date<1 || date>31 )
								{
									System.out.println("���ָ�ʽ����ȷ��������1-31֮�������:");
									date = in.nextInt();
								}
								else if ( date<dates[i] )
								{
									System.out.println("�黹���ڲ���С�ڽ������ڣ�����������:");
									date = in.nextInt();
								}
								
							}
							
							System.out.println("�黹��"+returnBook+"���ɹ���");
							states[i] = 0;	//�������ý���״̬
							//�������
							int rent = ( date-dates[i] )*1;
							System.out.println("Ӧ�����Ԫ��:"+rent);
							dates[i] = 0;	//�������ý�������
							break;
						}
						else if ( names[i].equals( returnBook ) && states[i] == 0 )
						{
							//�ҵ�����Ҫ����飬���״̬���ѽ���ģ������Խ�
							System.out.println("������δ���������黹��");
							break;
						}
					}
					break;
				case 5:
					//ɾ��ͼ��
					System.out.println("\nɾ��ͼ��-->");
					System.out.println("������ɾ��ͼ������-->");
					String deleteBook = in.next();
					//Ҫɾ��ͼ���λ��
					int index = -1;
					for ( int i=0; i<names.length; i++ )
					{
						if ( names[i] == null )
						{
							//û�ҵ�Ҫɾ������
							System.out.println("û�ҵ���Ҫɾ�����飬ɾ����");
							break;
						}
						else if ( names[i].equals(deleteBook) && states[i] == 1 )
						{
							//�ҵ�����Ҫɾ���飬���Ǹ����Ѿ����ȥ��
							System.out.println("�����Ѿ����ȥ�ˣ��޷�ɾ����");
							break;
						}
						else if ( names[i].equals(deleteBook) && states[i] == 0 )
						{
							//�ҵ���Ҫɾ�����飬����û���ȥ
							//��¼�����λ��
							index = i;
							break;
						}
					}
					//index������Ȼ��-1��Ҳ������һ��������λ��
					//����ͼ���λ�ý���ɾ����������ͼ��λ��������ǰ����
					if ( index != -1 ) 
					{
						//��index-��������һ����һ����ǰ����
						for ( int i=index; i<names.length; i++ )
						{
							if ( i != names.length-1)
							{
								names[i] = names[i+1];
								counts[i] = counts[i+1];
								dates[i] = dates[i+1];
								states[i] = states[i+1];
							}
							//�����һ��Ԫ���ÿ�
							names[names.length-1] = null;
							counts[names.length-1] = 0;
							dates[names.length-1] = 0;
							states[names.length-1] = 0;
						}
						System.out.println("ɾ���ɹ�");
					}
					
					break;
				case 6:
					//����ѭ��
					flag = false;
					break;
				default:
					//�����˴�������
					flag = false;
					break;
						
			}
			//���flag == false,�����ѭ��������ϵͳ
			if ( !flag )
			{
				//����ѭ��
				break;
			}
			else
			{
				//�û�������ʹ��ϵͳ�������û��뷵�����˵���������
				System.out.println("������0����");
				num = in.nextInt();
			}
			
		}
		while( num == 0 );
		System.out.println("лл����ӭʹ�ã�");
		
	}

}
