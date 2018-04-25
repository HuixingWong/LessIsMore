package com.example.dogoodsoft_app.lessismore.retrofit;


@SuppressWarnings("all")
public class Article {
    private final Data data;

    public Article(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public static class Data {
        private final Date date;

        private final String author;

        private final String title;

        private final String digest;

        private final String content;

        private final int wc;

        public Data(Date date, String author, String title, String digest, String content, int wc) {
            this.date = date;
            this.author = author;
            this.title = title;
            this.digest = digest;
            this.content = content;
            this.wc = wc;
        }

        public Date getDate() {
            return date;
        }

        public String getAuthor() {
            return author;
        }

        public String getTitle() {
            return title;
        }

        public String getDigest() {
            return digest;
        }

        public String getContent() {
            return content;
        }

        public int getWc() {
            return wc;
        }

        public static class Date {
            private final String curr;

            private final String prev;

            private final String next;

            public Date(String curr, String prev, String next) {
                this.curr = curr;
                this.prev = prev;
                this.next = next;
            }

            public String getCurr() {
                return curr;
            }

            public String getPrev() {
                return prev;
            }

            public String getNext() {
                return next;
            }
        }
    }
}
