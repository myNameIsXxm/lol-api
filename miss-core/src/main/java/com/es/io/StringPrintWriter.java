package com.es.io;

import java.io.PrintWriter;
import java.io.StringWriter;
/**
 * ��չPrintWriter
 */
@Deprecated
public class StringPrintWriter extends PrintWriter{  
	  
    public StringPrintWriter(){  
        super(new StringWriter());  
    }  
     
    public StringPrintWriter(int initialSize) {  
          super(new StringWriter(initialSize));  
    }  
     
    public String getString() {  
          flush();  
          return ((StringWriter) this.out).toString();  
    }  
     
    @Override  
    public String toString() {  
        return getString();  
    }  
}