import java.io.*;

public class CharStack
{
    private char[] m_data;           // See Note #1 below
    private int m_ptr;

    public CharStack(int size)
    {
        m_ptr = 0;                   // Note #2
        m_data = new char[(size > 1 ? size : 10)];
    }

    public void push(char c)
    {
        if (m_ptr >= m_data.length) // Note #3
        {
            // Grow the array automatically
            char[] tmp = new char[m_data.length * 2];
            System.arraycopy(m_data, 0, tmp, 0, m_data.length);
            m_data = tmp;
        }
        m_data[m_ptr++] = c;
    }

    public char pop() throws StackUnderFlowException             // Note #4
    {
        if(m_ptr == 0){
            throw new StackUnderFlowException("Error: stack underflow!");
        }
        // Stack underflow: An error condition that occurs when an item is called for from the stack, but the stack is empty.
        return m_data[--m_ptr];

    }

    public boolean hasMoreElements()
    {
        return (m_ptr != 0);
    }

    // Note #5
    public static void main(String[] argv) throws IOException, StackUnderFlowException {
        CharStack s = new CharStack(10);
        s.push('a');
        s.push('b');
        s.push('c');

        System.out.write(s.pop());
        System.out.write(s.pop());
        System.out.write(s.pop());
        System.out.write(s.pop()); // this will throw a stack underflow error


        // You will need to press an end-of-file character: Command + D

//        int i;
//        while ( (i = System.in.read()) != -1 )
//        {
//            s.push((char) i);
//        }
//        while (s.hasMoreElements())
//        {
//            System.out.write(s.pop());
//        }
        System.out.println();
    }
}