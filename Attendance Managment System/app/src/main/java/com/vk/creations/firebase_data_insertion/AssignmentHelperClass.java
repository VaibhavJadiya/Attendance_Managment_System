package com.vk.creations.firebase_data_insertion;

class AssignmentHelperClass {
    String name,subject,link;

    public AssignmentHelperClass() {
    }

    public AssignmentHelperClass(String name, String subject, String link) {
        this.name = name;
        this.subject = subject;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
