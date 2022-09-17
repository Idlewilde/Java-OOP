public class Player {

        private String name;
        private int endurance;
        private int sprint;
        private int dribble;
        private int passing;
        private int shooting;

        public Player(String name, int endurance, int sprint, int dribble, int passing, int shooting) {
            this.setName(name);
            this.setEndurance(endurance);
            this.setSprint(sprint);
            this.setDribble(dribble);
            this.setPassing(passing);
            this.setShooting(shooting);
        }

        public String getName() {
            return name;
        }

        private void setName(String name) {
            if(name.trim().isEmpty()){
                throw new IllegalArgumentException("A name should not be empty.");}
            this.name = name;

        }

        private void setEndurance(int endurance) {
            checkRange(endurance,"Endurance");
            this.endurance = endurance;
        }

        private void setSprint(int sprint) {
            checkRange(sprint,"Sprint");
            this.sprint = sprint;
        }

        private void setDribble(int dribble) {
            checkRange(dribble,"Dribble");
            this.dribble = dribble;
        }

        private void setPassing(int passing) {
            checkRange(passing,"Passing");
            this.passing = passing;
        }

        private void setShooting(int shooting) {
            checkRange(shooting,"Shooting");
            this.shooting = shooting;
        }

        public double overallSkillLevel(){
            return (this.dribble+this.passing+this.endurance+this.shooting+this.sprint)/5.0;

        }

        private void checkRange(int value, String name){
            if(value<0||value>100){
                throw new IllegalArgumentException(name+" should be between 0 and 100.");}
        }
    }


