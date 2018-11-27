package com.fengping.demo.utils;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import org.apache.commons.lang3.Validate;

public class StorePathUtil {

    private static final String group = "group";

    public static StorePath praseFromUrl(String filePath) {
        Validate.notNull(filePath, "解析文件路径不能为空", new Object[0]);
        int groupStartPos = getGroupStartPos(filePath);
        String groupAndPath = filePath.substring(groupStartPos + group.length());
        int pos = groupAndPath.indexOf("/");
        if (pos > 0 && pos != groupAndPath.length() - 1) {
            String group = groupAndPath.substring(0, pos);
            String path = groupAndPath.substring(pos + 1);
            return new StorePath(group, path);
        } else {
            throw new FdfsUnsupportStorePathException("解析文件路径错误,有效的路径样式为(group组名/path路径名) 而当前解析路径为".concat(filePath));
        }
    }

    private static int getGroupStartPos(String filePath) {
        int pos = filePath.indexOf(group);
        if (pos == -1) {
            throw new FdfsUnsupportStorePathException("解析文件路径错误,被解析路径url没有group,当前解析路径为".concat(filePath));
        } else {
            return pos;
        }
    }

    public static String getFilePath(String filePath){
        Validate.notNull(filePath, "解析文件路径不能为空", new Object[0]);
        int groupStartPos = getGroupStartPos(filePath);
        String groupAndPath = filePath.substring(groupStartPos + group.length());
        int pos = groupAndPath.indexOf("/");
        if (pos > 0 && pos != groupAndPath.length() - 1) {
            String path = groupAndPath.substring(pos + 1);
            return path;
        } else {
            throw new FdfsUnsupportStorePathException("解析文件路径错误,有效的路径样式为(group组名/path路径名) 而当前解析路径为".concat(filePath));
        }
    }

}
