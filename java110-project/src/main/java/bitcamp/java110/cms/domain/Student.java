package bitcamp.java110.cms.domain;

public class Student extends Member {
        private static final long serialVersionUID = 1L;
        protected String school;
        protected boolean working;
        
        public String getSchool() {
            return school;
        }
        public void setSchool(String school) {
            this.school = school;
        }
        public boolean isWorking() {    //boolean의 get은 is로!
            return working;
        }
        public void setWorking(boolean working) {
            this.working = working;
        }
        
    
    
}
