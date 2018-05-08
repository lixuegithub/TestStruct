package com.adinnet.struct.bean;

import java.util.List;

public class PhoneBean {

    /**
     * count : 2
     * items : [{"author":"缪洋洋","articleSource":"测试","beforeTime":15816,"id":"c027c99941874280958dd6aafd56a0f3","pic":"","readCount":0,"summary":"缪洋洋","title":"测试信息2222"},{"author":"缪洋洋","articleSource":"测试","beforeTime":15817,"id":"9b06e0b440464f38ba9358a7416a14f2","pic":"/pic/2017122915440772560.jpg","readCount":0,"summary":"测试数据、测试完毕删除、","title":"测试资讯信息展示"}]
     */

    private int count;
    private List<ItemsBean> items;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * author : 缪洋洋
         * articleSource : 测试
         * beforeTime : 15816
         * id : c027c99941874280958dd6aafd56a0f3
         * pic :
         * readCount : 0
         * summary : 缪洋洋
         * title : 测试信息2222
         */

        private String author;
        private String articleSource;
        private int beforeTime;
        private String id;
        private String pic;
        private int readCount;
        private long createdDate;
        private String summary;
        private String title;


        public long getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(long createdDate) {
            this.createdDate = createdDate;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getArticleSource() {
            return articleSource;
        }

        public void setArticleSource(String articleSource) {
            this.articleSource = articleSource;
        }

        public int getBeforeTime() {
            return beforeTime;
        }

        public void setBeforeTime(int beforeTime) {
            this.beforeTime = beforeTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getReadCount() {
            return readCount;
        }

        public void setReadCount(int readCount) {
            this.readCount = readCount;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
