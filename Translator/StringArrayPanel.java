/**Translator Project
 * @author Yun (Ann) Zhou
 * *@version Fall 2019
 * *CSci 1130
 **/

public class StringArrayPanel {

    /**
     * @return index of target in arr else -1 if not found
     * */

    private static String[] englishDict = {"rat", "ox", "tiger", "rabbit"
            , "dragon", "snake", "horse", "goat"
            , "monkey", "rooster", "dog", "pig"};

    private static String[] chineseDict = {"鼠", "牛", "虎", "兔"
            , "龙", "蛇", "马", "羊"
            , "猴", "鸡", "狗", "猪"};

    public static String translateToChinese(String englishWord) {

        int found = sequentialSearch(englishWord, englishDict);
        
        if (found >= 0) {
            return chineseDict[found];
        }
        else {
            return "Not Found";
        }

    }

    public static int sequentialSearch(String target, String[] arr){
        int count=0;
        int foundIndex=-1;
        while(count<arr.length&&foundIndex==-1){
            if(arr[count].equalsIgnoreCase(target)){
                foundIndex=count;
            }
            count++;
        }
        return foundIndex;
    }
} // end of StringArrayPanel