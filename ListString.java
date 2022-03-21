public class ListString {

private static class StringItem

{

private int len;

private StringItem next;

private char [] symbols;

final static byte SIZE=16;

public StringItem (int l, char [] s, StringItem n)

{

len=l;

symbols=s;

next=n;

}

public StringItem (StringItem a, char[] c, StringItem b)

{

len=a.len;

symbols=c;

next=b;

}

}

// представляет номер и элемент в списке

public class Pos

{

private int num;

private StringItem a;

private Pos (StringItem b, int n)

{

a=b;

num=n;

}

}

public StringItem head;

public ListString(String a)

{

head=toStr(a);

}

public ListString()

{

head=null;

}

public ListString(ListString s)

{

StringItem h=s.head;

head=new StringItem(h, copy (0,h.len, h.symbols), null);

StringItem p=head;

h=h.next;

while(h!=null)

{

p=add(h.len, copy (0,h.len, h.symbols), p);

h=h.next;

}

}

//Вставка в конец символа

public void append(char d)

{

String r=Character.toString(d);

ListString b= new ListString(r);

StringItem s=TheEnd();

s.next=b.head;

}

//Вставка в конец строки ListString

public void append(ListString d)

{

StringItem s=TheEnd();

ListString c=new ListString(d);

s.next=c.head;

}

//Вставка в конец строки String

public void append(String g)

{

ListString d=new ListString(g);

append(d);

}

//замена символа в позиции index на d

public void setcharAt(int index, char d)

{

Pos b=findblock(index);

b.a.symbols[b.num]=d;

}

//символ в строке в позиции index

public char charAt(int index)

{

Pos c=findblock(index);

return c.a.symbols[c.num];

}

public ListString substring(int s, int e)

{

StringItem ss=cutblock(findblock(s)), ee=cutblock(findblock(e));

ListString res= new ListString();

res.head = new StringItem(ss.len, copy (0,ss.len, ss.symbols), null);

StringItem p=res.head;

ss=ss.next;

while (ss!=ee.next)

{

p = add(ss.len, copy (0,ss.len, ss.symbols), p);

ss=ss.next;

}

return res;

}

//длина

public int length()

{

int k=0;

StringItem h=head;

while (h!=null)

{

k+=h.len;

h=h.next;

}

return k;

}

public void insert (String c, int k)

{

insert(new ListString(c), k);

}

public void insert (ListString c, int k)

{

vstavka(new ListString(c), cutblock(findblock(k)));

}

//текстовое представление

public String toString()

{

String str=new String(head.symbols, 0, head.len);

StringItem h=head.next;

while (h!=null)

{

String n= new String(h.symbols, 0, h.len);

str+=n;

h=h.next;

}

return str;

}

//вставка

private void vstavka (ListString h, StringItem fr)

{

StringItem p=fr.next, k;

fr.next=h.head;

k=h.TheEnd();

k.next=p;

}

private StringItem cutblock(Pos dr)

{

int k=dr.num, h=dr.a.len-k;

if (dr.a.len!=k)

{

char[] c1 = copy(0, dr.num, dr.a.symbols);

char[] c2 = copy(dr.num, dr.a.len, dr.a.symbols);

dr.a.symbols=c1;

dr.a.len=k;

dr.a.next= new StringItem(h, c2, dr.a.next);

}

return dr.a;

}

private Pos findblock(int index)

{

StringItem h=head;

while (h.len<index)

{

index-=h.len;

h=h.next;

}

return new Pos(h, index);

}

private StringItem toStr(String b)

{

char[] mk=b.toCharArray();

StringItem h=null;

StringItem p=h;

int i=0;

while (i<mk.length)

{

int j=0;

char[] am=new char[16];

while ((j<16)&& (i<mk.length))

{

am[j]=mk[i];

i++;

j++;

}

if (h==null) {

h = new StringItem(j,am, h);

p=h;

}

else p=add(j,am, p);

}

return h;

}

private StringItem add(int val, char [] m ,StringItem b)

{

b.next=new StringItem(val, m, b.next);

return b.next;

}

private StringItem TheEnd ()

{

StringItem h=head;

StringItem p=h.next;

while (p!=null)

{

if (p.len+h.len<=StringItem.SIZE)

{

int i=h.len, j;

for (j=0; j<p.len; j++)

{

h.symbols[i]=p.symbols[j];

i++;

}

h.len+=p.len;

p=p.next;

h.next=p;

}

else

{

p=p.next;

h=h.next;

}

}

return h;

}

private char[] copy (int s, int e, char[] a)

{

char[] v=new char[StringItem.SIZE];

int i, j=-1;

for(i=s; i<e; i++)

{

j++;

v[j]=a[i];

}

return v;

}

}
