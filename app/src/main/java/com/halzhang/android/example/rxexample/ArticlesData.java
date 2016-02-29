package com.halzhang.android.example.rxexample;

import java.util.List;

/**
 * Created by Hal on 15/4/26.
 */
public class ArticlesData {


    public int version;
    public boolean hasMore;
    public List<Aritcle> articles;
    public int  columnId;

    public static class Aritcle {
        public int fileId;
        public String title;
        public String keyWord;
        public String version;
        public String attAbstract;
        public String introtitle;
        public int articleType;
        public String extproperty;
        public String publishtime;
        public String imageUrl;
        public String phoneImageUrl;
        public String videoUrl;
        public String contentUrl;
        public String shareUrl;
        public String picCount;
        public String articleContent;
        public String imageSize;
        public String audioUrl;
        public int publishState;
        public int nodeId;
        public String driverTypes;
        public String artiposition;
        public int fixedorder;
        public String[] threePhotosUrl;
        public String category;
        public int readCount;
        public int commentCount;
        public int shareCount;
        public int shareReadCount;
        public int greatCount;
        public String discount;
        public String discountUrl;
        public int closeComment;


        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Aritcle{");
            sb.append("fileId=").append(fileId);
            sb.append(", title='").append(title).append('\'');
            sb.append(", contentUrl='").append(contentUrl).append('\'');
            sb.append(", publishtime='").append(publishtime).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ArticlesData{");
        sb.append("version=").append(version);
        sb.append(", hasMore=").append(hasMore);
        sb.append(", columnId='").append(columnId);
        sb.append(", articles=").append(articles);
        sb.append('}');
        return sb.toString();
    }
}
