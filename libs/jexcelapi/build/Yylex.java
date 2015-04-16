/* The following code was generated by JFlex 1.4.1 on 24/10/09 14:10 */

/*********************************************************************
 *
 *      Copyright (C) 2002 Andrew Khan
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 ***************************************************************************/

package jxl.biff.formula;

/**
 * This file is generated by JLex.  Do not alter the contents of this file
 * because changes will be overridden
 */

import jxl.biff.WorkbookMethods;


/**
 * This class is a scanner generated by
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.1
 * on 24/10/09 14:10 from the specification file
 * <tt>xlformula.flex</tt>
 */
class Yylex {

    /**
     * This character denotes the end of file
     */
    public static final int YYEOF = -1;

    /**
     * initial size of the lookahead buffer
     */
    private static final int ZZ_BUFFERSIZE = 16384;

    /**
     * lexical states
     */
    public static final int YYSTRING = 1;
    public static final int YYINITIAL = 0;

    /**
     * Translates characters to character classes
     */
    private static final String ZZ_CMAP_PACKED =
            "\10\0\3\25\25\0\1\25\1\24\1\21\1\26\1\10\2\0\1\22" +
                    "\1\5\1\6\1\41\1\37\1\4\1\40\1\7\1\33\1\34\11\2" +
                    "\1\3\1\0\1\44\1\43\1\42\1\36\1\0\1\16\2\1\1\30" +
                    "\1\14\1\15\2\1\1\31\2\1\1\17\1\35\1\27\3\1\1\12" +
                    "\1\20\1\11\1\13\1\32\4\1\4\0\1\23\1\0\32\1\uff85\0";

    /**
     * Translates characters to character classes
     */
    private static final char[] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

    /**
     * Translates DFA states to action switch labels.
     */
    private static final int[] ZZ_ACTION = zzUnpackAction();

    private static final String ZZ_ACTION_PACKED_0 =
            "\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7" +
                    "\1\0\2\2\1\10\1\0\1\11\1\0\1\12\1\13" +
                    "\1\14\1\15\1\16\1\17\1\20\1\1\1\21\1\2" +
                    "\1\22\1\0\1\23\1\0\1\2\3\0\2\2\5\0" +
                    "\1\24\1\25\1\26\1\2\1\0\1\27\1\0\1\22" +
                    "\2\0\1\30\1\0\2\2\10\0\1\27\1\0\1\31" +
                    "\1\0\1\32\10\0\1\33\2\0\1\31\2\0\1\34" +
                    "\4\0\1\35\3\0\1\35\1\0\1\36\1\0";

    private static int[] zzUnpackAction() {
        int[] result = new int[94];
        int offset = 0;
        offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
        return result;
    }

    private static int zzUnpackAction(String packed, int offset, int[] result) {
        int i = 0;       /* index in packed string  */
        int j = offset;  /* index in unpacked array */
        int l = packed.length();
        while (i < l) {
            int count = packed.charAt(i++);
            int value = packed.charAt(i++);
            do result[j++] = value; while (--count > 0);
        }
        return j;
    }


    /**
     * Translates a state to a row index in the transition table
     */
    private static final int[] ZZ_ROWMAP = zzUnpackRowMap();

    private static final String ZZ_ROWMAP_PACKED_0 =
            "\0\0\0\45\0\112\0\157\0\224\0\224\0\224\0\224" +
                    "\0\271\0\336\0\u0103\0\224\0\u0128\0\224\0\u014d\0\224" +
                    "\0\224\0\224\0\224\0\u0172\0\224\0\u0197\0\u01bc\0\224" +
                    "\0\u01e1\0\u0206\0\u022b\0\224\0\u0250\0\u0275\0\u029a\0\u02bf" +
                    "\0\u02e4\0\u0309\0\u032e\0\u0353\0\u0378\0\u039d\0\u03c2\0\u03e7" +
                    "\0\224\0\224\0\224\0\u040c\0\u0431\0\u0456\0\u047b\0\u04a0" +
                    "\0\u04c5\0\u04ea\0\u02bf\0\u050f\0\u0534\0\u0559\0\u057e\0\u05a3" +
                    "\0\u05c8\0\u05ed\0\u0612\0\u0637\0\u065c\0\u0681\0\224\0\u06a6" +
                    "\0\u06cb\0\u06cb\0\u040c\0\u06f0\0\u0715\0\u073a\0\u075f\0\u0784" +
                    "\0\u07a9\0\u07ce\0\u07f3\0\u0818\0\u0818\0\u083d\0\u0862\0\u0887" +
                    "\0\u08ac\0\224\0\u08d1\0\u08f6\0\u091b\0\u0940\0\u0965\0\u098a" +
                    "\0\u09af\0\u09d4\0\224\0\u09f9\0\u0a1e\0\u0a1e";

    private static int[] zzUnpackRowMap() {
        int[] result = new int[94];
        int offset = 0;
        offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
        return result;
    }

    private static int zzUnpackRowMap(String packed, int offset, int[] result) {
        int i = 0;  /* index in packed string  */
        int j = offset;  /* index in unpacked array */
        int l = packed.length();
        while (i < l) {
            int high = packed.charAt(i++) << 16;
            result[j++] = high | packed.charAt(i++);
        }
        return j;
    }

    /**
     * The transition table of the DFA
     */
    private static final int[] ZZ_TRANS = zzUnpackTrans();

    private static final String ZZ_TRANS_PACKED_0 =
            "\1\0\1\3\1\4\1\5\1\6\1\7\1\10\1\0" +
                    "\1\11\1\12\3\3\1\13\3\3\1\14\1\15\2\0" +
                    "\1\16\1\17\4\3\1\20\1\4\1\3\1\0\1\21" +
                    "\1\22\1\23\1\24\1\25\1\26\21\27\1\30\23\27" +
                    "\1\0\1\31\1\32\1\33\1\0\1\34\2\0\1\35" +
                    "\10\31\2\0\1\36\1\37\2\0\4\31\1\0\1\32" +
                    "\1\31\11\0\1\4\4\0\1\40\24\0\1\4\56\0" +
                    "\1\41\7\0\10\41\6\0\4\41\2\0\1\41\10\0" +
                    "\1\31\1\32\1\33\1\0\1\34\2\0\1\35\1\31" +
                    "\1\42\6\31\2\0\1\36\1\37\2\0\4\31\1\0" +
                    "\1\32\1\31\10\0\1\31\1\32\1\33\1\0\1\34" +
                    "\2\0\1\35\5\31\1\43\2\31\2\0\1\36\1\37" +
                    "\2\0\4\31\1\0\1\32\1\31\7\0\22\15\1\44" +
                    "\22\15\12\0\1\45\14\0\1\46\1\47\1\0\1\50" +
                    "\55\0\1\51\43\0\1\52\1\53\1\0\21\27\1\0" +
                    "\23\27\1\0\1\54\1\32\1\33\1\0\1\34\2\0" +
                    "\1\35\10\54\2\0\1\36\1\37\2\0\4\54\1\0" +
                    "\1\32\1\54\10\0\1\36\1\32\1\55\5\0\10\36" +
                    "\2\0\1\36\3\0\4\36\1\0\1\32\1\36\10\0" +
                    "\1\56\6\0\1\57\10\56\6\0\4\56\2\0\1\56" +
                    "\11\0\1\60\31\0\1\60\11\0\2\36\6\0\10\36" +
                    "\2\0\1\36\3\0\4\36\1\0\2\36\10\0\1\61" +
                    "\6\0\1\62\10\61\6\0\4\61\2\0\1\61\11\0" +
                    "\1\63\31\0\1\63\11\0\1\64\1\60\1\33\4\0" +
                    "\1\35\10\64\6\0\4\64\1\0\1\60\1\64\10\0" +
                    "\1\54\1\32\1\33\1\0\1\34\2\0\1\35\2\54" +
                    "\1\65\5\54\2\0\1\36\1\37\2\0\4\54\1\0" +
                    "\1\32\1\54\10\0\1\54\1\32\1\33\1\0\1\34" +
                    "\2\0\1\35\6\54\1\66\1\54\2\0\1\36\1\37" +
                    "\2\0\4\54\1\0\1\32\1\54\33\0\1\67\34\0" +
                    "\1\70\43\0\1\71\2\0\1\72\57\0\1\73\31\0" +
                    "\1\74\27\0\1\54\1\36\2\0\1\34\3\0\10\54" +
                    "\2\0\1\36\1\37\2\0\4\54\1\0\1\36\1\54" +
                    "\10\0\1\75\6\0\1\76\10\75\6\0\4\75\2\0" +
                    "\1\75\10\0\1\77\7\0\10\77\6\0\4\77\2\0" +
                    "\1\77\10\0\1\56\7\0\10\56\6\0\4\56\2\0" +
                    "\1\56\11\0\1\60\1\55\30\0\1\60\11\0\1\100" +
                    "\1\101\5\0\1\102\10\100\6\0\4\100\1\0\1\101" +
                    "\1\100\10\0\1\61\7\0\10\61\6\0\4\61\2\0" +
                    "\1\61\11\0\1\60\1\33\4\0\1\35\23\0\1\60" +
                    "\11\0\1\54\1\36\2\0\1\34\3\0\3\54\1\103" +
                    "\4\54\2\0\1\36\1\37\2\0\4\54\1\0\1\36" +
                    "\1\54\10\0\1\54\1\36\2\0\1\34\3\0\7\54" +
                    "\1\65\2\0\1\36\1\37\2\0\4\54\1\0\1\36" +
                    "\1\54\10\0\1\104\6\0\1\105\10\104\6\0\4\104" +
                    "\2\0\1\104\24\0\1\106\46\0\1\107\15\0\1\106" +
                    "\44\0\1\110\41\0\1\111\31\0\1\112\26\0\1\113" +
                    "\1\114\5\0\1\115\10\113\6\0\4\113\1\0\1\114" +
                    "\1\113\10\0\1\75\7\0\10\75\6\0\4\75\2\0" +
                    "\1\75\11\0\1\101\5\0\1\102\23\0\1\101\12\0" +
                    "\1\101\31\0\1\101\11\0\1\116\1\117\1\120\4\0" +
                    "\1\121\10\116\6\0\4\116\1\0\1\117\1\116\10\0" +
                    "\1\104\7\0\10\104\6\0\4\104\2\0\1\104\33\0" +
                    "\1\122\37\0\1\106\41\0\1\123\63\0\1\124\24\0" +
                    "\1\125\33\0\1\114\5\0\1\115\23\0\1\114\12\0" +
                    "\1\114\31\0\1\114\12\0\1\117\1\120\4\0\1\121" +
                    "\23\0\1\117\12\0\1\117\1\126\30\0\1\117\11\0" +
                    "\1\127\6\0\1\130\10\127\6\0\4\127\2\0\1\127" +
                    "\11\0\1\117\31\0\1\117\46\0\1\122\42\0\1\106" +
                    "\24\0\1\106\31\0\1\131\6\0\1\132\10\131\6\0" +
                    "\4\131\2\0\1\131\10\0\1\133\7\0\10\133\6\0" +
                    "\4\133\2\0\1\133\10\0\1\127\7\0\10\127\6\0" +
                    "\4\127\2\0\1\127\10\0\1\134\1\135\5\0\1\136" +
                    "\10\134\6\0\4\134\1\0\1\135\1\134\10\0\1\131" +
                    "\7\0\10\131\6\0\4\131\2\0\1\131\11\0\1\135" +
                    "\5\0\1\136\23\0\1\135\12\0\1\135\31\0\1\135" +
                    "\10\0";

    private static int[] zzUnpackTrans() {
        int[] result = new int[2627];
        int offset = 0;
        offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
        return result;
    }

    private static int zzUnpackTrans(String packed, int offset, int[] result) {
        int i = 0;       /* index in packed string  */
        int j = offset;  /* index in unpacked array */
        int l = packed.length();
        while (i < l) {
            int count = packed.charAt(i++);
            int value = packed.charAt(i++);
            value--;
            do result[j++] = value; while (--count > 0);
        }
        return j;
    }


    /* error codes */
    private static final int ZZ_UNKNOWN_ERROR = 0;
    private static final int ZZ_NO_MATCH = 1;
    private static final int ZZ_PUSHBACK_2BIG = 2;

    /* error messages for the codes above */
    private static final String ZZ_ERROR_MSG[] = {
            "Unkown internal scanner error",
            "Error: could not match input",
            "Error: pushback value was too large"
    };

    /**
     * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
     */
    private static final int[] ZZ_ATTRIBUTE = zzUnpackAttribute();

    private static final String ZZ_ATTRIBUTE_PACKED_0 =
            "\1\0\3\1\4\11\1\0\2\1\1\11\1\0\1\11" +
                    "\1\0\4\11\1\1\1\11\2\1\1\11\2\1\1\0" +
                    "\1\11\1\0\1\1\3\0\2\1\5\0\3\11\1\1" +
                    "\1\0\1\1\1\0\1\1\2\0\1\1\1\0\2\1" +
                    "\10\0\1\11\1\0\1\1\1\0\1\1\10\0\1\1" +
                    "\2\0\1\1\2\0\1\11\4\0\1\1\3\0\1\11" +
                    "\1\0\1\1\1\0";

    private static int[] zzUnpackAttribute() {
        int[] result = new int[94];
        int offset = 0;
        offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
        return result;
    }

    private static int zzUnpackAttribute(String packed, int offset, int[] result) {
        int i = 0;       /* index in packed string  */
        int j = offset;  /* index in unpacked array */
        int l = packed.length();
        while (i < l) {
            int count = packed.charAt(i++);
            int value = packed.charAt(i++);
            do result[j++] = value; while (--count > 0);
        }
        return j;
    }

    /**
     * the input device
     */
    private java.io.Reader zzReader;

    /**
     * the current state of the DFA
     */
    private int zzState;

    /**
     * the current lexical state
     */
    private int zzLexicalState = YYINITIAL;

    /**
     * this buffer contains the current text to be matched and is
     * the source of the yytext() string
     */
    private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

    /**
     * the textposition at the last accepting state
     */
    private int zzMarkedPos;

    /**
     * the textposition at the last state to be included in yytext
     */
    private int zzPushbackPos;

    /**
     * the current text position in the buffer
     */
    private int zzCurrentPos;

    /**
     * startRead marks the beginning of the yytext() string in the buffer
     */
    private int zzStartRead;

    /**
     * endRead marks the last character in the buffer, that has been read
     * from input
     */
    private int zzEndRead;

    /**
     * number of newlines encountered up to the start of the matched text
     */
    private int yyline;

    /**
     * the number of characters up to the start of the matched text
     */
    private int yychar;

    /**
     * the number of characters from the last newline up to the start of the
     * matched text
     */
    private int yycolumn;

    /**
     * zzAtBOL == true <=> the scanner is currently at the beginning of a line
     */
    private boolean zzAtBOL = true;

    /**
     * zzAtEOF == true <=> the scanner is at the EOF
     */
    private boolean zzAtEOF;

    /* user code: */
    int getPos() {
        return yychar;
    }

    private boolean emptyString;
    private ExternalSheet externalSheet;
    private WorkbookMethods nameTable;

    void setExternalSheet(ExternalSheet es) {
        externalSheet = es;
    }

    void setNameTable(WorkbookMethods nt) {
        nameTable = nt;
    }


    /**
     * Creates a new scanner
     * There is also a java.io.InputStream version of this constructor.
     *
     * @param in the java.io.Reader to read input from.
     */
    Yylex(java.io.Reader in) {
        this.zzReader = in;
    }

    /**
     * Creates a new scanner.
     * There is also java.io.Reader version of this constructor.
     *
     * @param in the java.io.Inputstream to read input from.
     */
    Yylex(java.io.InputStream in) {
        this(new java.io.InputStreamReader(in));
    }

    /**
     * Unpacks the compressed character translation table.
     *
     * @param packed the packed character translation table
     * @return the unpacked character translation table
     */
    private static char[] zzUnpackCMap(String packed) {
        char[] map = new char[0x10000];
        int i = 0;  /* index in packed string  */
        int j = 0;  /* index in unpacked array */
        while (i < 100) {
            int count = packed.charAt(i++);
            char value = packed.charAt(i++);
            do map[j++] = value; while (--count > 0);
        }
        return map;
    }


    /**
     * Refills the input buffer.
     *
     * @return <code>false</code>, iff there was new input.
     * @throws java.io.IOException if any I/O-Error occurs
     */
    private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
        if (zzStartRead > 0) {
            System.arraycopy(zzBuffer, zzStartRead,
                    zzBuffer, 0,
                    zzEndRead - zzStartRead);

      /* translate stored positions */
            zzEndRead -= zzStartRead;
            zzCurrentPos -= zzStartRead;
            zzMarkedPos -= zzStartRead;
            zzPushbackPos -= zzStartRead;
            zzStartRead = 0;
        }

    /* is the buffer big enough? */
        if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
            char newBuffer[] = new char[zzCurrentPos * 2];
            System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
            zzBuffer = newBuffer;
        }

    /* finally: fill the buffer with new input */
        int numRead = zzReader.read(zzBuffer, zzEndRead,
                zzBuffer.length - zzEndRead);

        if (numRead < 0) {
            return true;
        } else {
            zzEndRead += numRead;
            return false;
        }
    }


    /**
     * Closes the input stream.
     */
    public final void yyclose() throws java.io.IOException {
        zzAtEOF = true;            /* indicate end of file */
        zzEndRead = zzStartRead;  /* invalidate buffer    */

        if (zzReader != null)
            zzReader.close();
    }


    /**
     * Resets the scanner to read from a new input stream.
     * Does not close the old reader.
     * <p/>
     * All internal variables are reset, the old input stream
     * <b>cannot</b> be reused (internal buffer is discarded and lost).
     * Lexical state is set to <tt>ZZ_INITIAL</tt>.
     *
     * @param reader the new input stream
     */
    public final void yyreset(java.io.Reader reader) {
        zzReader = reader;
        zzAtBOL = true;
        zzAtEOF = false;
        zzEndRead = zzStartRead = 0;
        zzCurrentPos = zzMarkedPos = zzPushbackPos = 0;
        yyline = yychar = yycolumn = 0;
        zzLexicalState = YYINITIAL;
    }


    /**
     * Returns the current lexical state.
     */
    public final int yystate() {
        return zzLexicalState;
    }


    /**
     * Enters a new lexical state
     *
     * @param newState the new lexical state
     */
    public final void yybegin(int newState) {
        zzLexicalState = newState;
    }


    /**
     * Returns the text matched by the current regular expression.
     */
    public final String yytext() {
        return new String(zzBuffer, zzStartRead, zzMarkedPos - zzStartRead);
    }


    /**
     * Returns the character at position <tt>pos</tt> from the
     * matched text.
     * <p/>
     * It is equivalent to yytext().charAt(pos), but faster
     *
     * @param pos the position of the character to fetch.
     *            A value from 0 to yylength()-1.
     * @return the character at position pos
     */
    public final char yycharat(int pos) {
        return zzBuffer[zzStartRead + pos];
    }


    /**
     * Returns the length of the matched text region.
     */
    public final int yylength() {
        return zzMarkedPos - zzStartRead;
    }


    /**
     * Reports an error that occured while scanning.
     * <p/>
     * In a wellformed scanner (no or only correct usage of
     * yypushback(int) and a match-all fallback rule) this method
     * will only be called with things that "Can't Possibly Happen".
     * If this method is called, something is seriously wrong
     * (e.g. a JFlex bug producing a faulty scanner etc.).
     * <p/>
     * Usual syntax/scanner level error handling should be done
     * in error fallback rules.
     *
     * @param errorCode the code of the errormessage to display
     */
    private void zzScanError(int errorCode) {
        String message;
        try {
            message = ZZ_ERROR_MSG[errorCode];
        } catch (ArrayIndexOutOfBoundsException e) {
            message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
        }

        throw new Error(message);
    }


    /**
     * Pushes the specified amount of characters back into the input stream.
     * <p/>
     * They will be read again by then next call of the scanning method
     *
     * @param number the number of characters to be read again.
     *               This number must not be greater than yylength()!
     */
    public void yypushback(int number) {
        if (number > yylength())
            zzScanError(ZZ_PUSHBACK_2BIG);

        zzMarkedPos -= number;
    }


    /**
     * Resumes scanning until the next regular expression is matched,
     * the end of input is encountered or an I/O-Error occurs.
     *
     * @return the next token
     * @throws java.io.IOException if any I/O-Error occurs
     */
    public ParseItem yylex() throws java.io.IOException, jxl.biff.formula.FormulaException {
        int zzInput;
        int zzAction;

        // cached fields:
        int zzCurrentPosL;
        int zzMarkedPosL;
        int zzEndReadL = zzEndRead;
        char[] zzBufferL = zzBuffer;
        char[] zzCMapL = ZZ_CMAP;

        int[] zzTransL = ZZ_TRANS;
        int[] zzRowMapL = ZZ_ROWMAP;
        int[] zzAttrL = ZZ_ATTRIBUTE;

        while (true) {
            zzMarkedPosL = zzMarkedPos;

            yychar += zzMarkedPosL - zzStartRead;

            boolean zzR = false;
            for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                 zzCurrentPosL++) {
                switch (zzBufferL[zzCurrentPosL]) {
                    case '\u000B':
                    case '\u000C':
                    case '\u0085':
                    case '\u2028':
                    case '\u2029':
                        yyline++;
                        zzR = false;
                        break;
                    case '\r':
                        yyline++;
                        zzR = true;
                        break;
                    case '\n':
                        if (zzR)
                            zzR = false;
                        else {
                            yyline++;
                        }
                        break;
                    default:
                        zzR = false;
                }
            }

            if (zzR) {
                // peek one character ahead if it is \n (if we have counted one line too much)
                boolean zzPeek;
                if (zzMarkedPosL < zzEndReadL)
                    zzPeek = zzBufferL[zzMarkedPosL] == '\n';
                else if (zzAtEOF)
                    zzPeek = false;
                else {
                    boolean eof = zzRefill();
                    zzEndReadL = zzEndRead;
                    zzMarkedPosL = zzMarkedPos;
                    zzBufferL = zzBuffer;
                    if (eof)
                        zzPeek = false;
                    else
                        zzPeek = zzBufferL[zzMarkedPosL] == '\n';
                }
                if (zzPeek) yyline--;
            }
            zzAction = -1;

            zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

            zzState = zzLexicalState;


            zzForAction:
            {
                while (true) {

                    if (zzCurrentPosL < zzEndReadL)
                        zzInput = zzBufferL[zzCurrentPosL++];
                    else if (zzAtEOF) {
                        zzInput = YYEOF;
                        break zzForAction;
                    } else {
                        // store back cached positions
                        zzCurrentPos = zzCurrentPosL;
                        zzMarkedPos = zzMarkedPosL;
                        boolean eof = zzRefill();
                        // get translated positions and possibly new buffer
                        zzCurrentPosL = zzCurrentPos;
                        zzMarkedPosL = zzMarkedPos;
                        zzBufferL = zzBuffer;
                        zzEndReadL = zzEndRead;
                        if (eof) {
                            zzInput = YYEOF;
                            break zzForAction;
                        } else {
                            zzInput = zzBufferL[zzCurrentPosL++];
                        }
                    }
                    int zzNext = zzTransL[zzRowMapL[zzState] + zzCMapL[zzInput]];
                    if (zzNext == -1) break zzForAction;
                    zzState = zzNext;

                    int zzAttributes = zzAttrL[zzState];
                    if ((zzAttributes & 1) == 1) {
                        zzAction = zzState;
                        zzMarkedPosL = zzCurrentPosL;
                        if ((zzAttributes & 8) == 8) break zzForAction;
                    }

                }
            }

            // store back cached position
            zzMarkedPos = zzMarkedPosL;

            switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
                case 12: {
                    return new Minus();
                }
                case 31:
                    break;
                case 7: {
                    return new CloseParentheses();
                }
                case 32:
                    break;
                case 3: {
                    return new IntegerValue(yytext());
                }
                case 33:
                    break;
                case 24: {
                    return new DoubleValue(yytext());
                }
                case 34:
                    break;
                case 29: {
                    return new ColumnRange3d(yytext(), externalSheet);
                }
                case 35:
                    break;
                case 4: {
                    return new RangeSeparator();
                }
                case 36:
                    break;
                case 10: {
                    return new Divide();
                }
                case 37:
                    break;
                case 25: {
                    return new CellReference3d(yytext(), externalSheet);
                }
                case 38:
                    break;
                case 26: {
                    return new BooleanValue(yytext());
                }
                case 39:
                    break;
                case 15: {
                    return new Equal();
                }
                case 40:
                    break;
                case 17: {
                    yybegin(YYINITIAL);
                    if (emptyString) return new StringValue("");
                }
                case 41:
                    break;
                case 8: {
                    emptyString = true;
                    yybegin(YYSTRING);
                }
                case 42:
                    break;
                case 21: {
                    return new NotEqual();
                }
                case 43:
                    break;
                case 22: {
                    return new LessEqual();
                }
                case 44:
                    break;
                case 16: {
                    return new LessThan();
                }
                case 45:
                    break;
                case 5: {
                    return new ArgumentSeparator();
                }
                case 46:
                    break;
                case 30: {
                    return new Area3d(yytext(), externalSheet);
                }
                case 47:
                    break;
                case 14: {
                    return new GreaterThan();
                }
                case 48:
                    break;
                case 18: {
                    return new CellReference(yytext());
                }
                case 49:
                    break;
                case 20: {
                    return new GreaterEqual();
                }
                case 50:
                    break;
                case 27: {
                    return new Area(yytext());
                }
                case 51:
                    break;
                case 23: {
                    return new ColumnRange(yytext());
                }
                case 52:
                    break;
                case 1: {
                    emptyString = false;
                    return new StringValue(yytext());
                }
                case 53:
                    break;
                case 2: {
                    return new NameRange(yytext(), nameTable);
                }
                case 54:
                    break;
                case 19: {
                    return new StringFunction(yytext());
                }
                case 55:
                    break;
                case 11: {
                    return new Plus();
                }
                case 56:
                    break;
                case 28: {
                    return new ErrorConstant(yytext());
                }
                case 57:
                    break;
                case 9: {
                }
                case 58:
                    break;
                case 13: {
                    return new Multiply();
                }
                case 59:
                    break;
                case 6: {
                    return new OpenParentheses();
                }
                case 60:
                    break;
                default:
                    if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
                        zzAtEOF = true;
                        return null;
                    } else {
                        zzScanError(ZZ_NO_MATCH);
                    }
            }
        }
    }


}
