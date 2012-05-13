#pragma once

#define MAXRESOLUTION		1280*720*4


class CDrawJpg
{
public:
	CDrawJpg();
	~CDrawJpg(void);
private:
	unsigned int m_iTotalJpgSize;
	unsigned int m_iRecvJpgSize;
	char m_bJpgSize[JPGSIZELEGNTH+1];
	BYTE * m_pJpgData;
	BYTE * m_pBitmapData;

	JPEG_CORE_PROPERTIES image;
	IJLERR err;
	BITMAP bit;	
	BITMAPINFO bmi;	

	int screenXSize;
	int screenYSize;

public:
	// �� �������� JPG ũ�� ������ ����
	void SetJpgInfo(char * data);
	void RecvJpgData(char * data, int packetSize);
	// �� ������ jpg ���
	void DrawScreen(void);
	
private:
	BOOL SetIJLInfo(void);
	void SetBitmapInfo(void);
	HWND screenHandle;
// 	HDC hdc;
// 	HDC memDC;
// 	HBITMAP bitmap;
// 	HBITMAP oldBitmap;
	
public:
	void InitDrawJpg(HWND screenHandle, int XSize, int YSize);
};
