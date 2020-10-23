package ru.skillbranch.devintensive.extentions

fun String.truncate(size : Int = 16) : String {
    val str = this.trim()
    return if (str.length <= size) str
    else "${str.substring(0, size).trim()}…"
}


fun String.stripHtml() : String {
    var res = this

    var start : Int = -1
    var finish : Int = -1
    //remove tags
    val arr = res.toCharArray()
    for (i in (arr.lastIndex downTo 0)){
        if (arr[i] == '>' && start < 0){
            finish = i
        }
        if (arr[i] == '<' && finish > 0){
            start = i
            res = res.removeRange(start..finish)
            start = -1
            finish = -1
        }
    }
    //remove html subsequences
    val subs = mutableListOf<String>()
    res.toCharArray().forEachIndexed { index, c ->
        if ( c == '&') start = index;
        if ( c == ' ' && start >= 0 ) {
            finish = index
            subs.add(res.substring(start..finish))
            start = -1;
            finish = -1;
        }
    }
    subs.forEach{ res = res.replace(it, "") }

    //remove spaces
    while (res.contains("  ")) {
        res = res.replace("  ", " ")
    }

    return res.trim()
}
