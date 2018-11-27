package com.fengping.demo.component;

import com.fengping.demo.utils.StorePathUtil;
import com.github.tobato.fastdfs.domain.MataData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.DefaultGenerateStorageClient;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Set;

@Component
public class MyFastFileStorageClientImpl extends DefaultGenerateStorageClient{
    @Deprecated
    public StorePath uploadFile(InputStream inputStream, long l, String s, Set<MataData> set) {
        return null;
    }

    @Deprecated
    public StorePath uploadImageAndCrtThumbImage(InputStream inputStream, long l, String s, Set<MataData> set) {
        return null;
    }

    public void deleteFile(String s) {
        StorePath storePath = StorePathUtil.praseFromUrl(s);
        super.deleteFile(storePath.getGroup(), storePath.getPath());
    }
}
