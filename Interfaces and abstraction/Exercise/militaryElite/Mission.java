package militaryElite;

public class Mission {
        private String codeName;
        private State state;

        public Mission(String codeName, State state) {
            setCodeName(codeName);
            setState(state);
        }

        public void completeMission() {
            setState(State.finished);
        }

        public String getCodeName() {
            return codeName;
        }

        private void setCodeName(String codeName) {
            this.codeName = codeName;
        }

        public State getState() {
            return state;
        }

        private void setState(State state) {
            this.state = state;
        }

        @Override
        public String toString() {
            return String.format("Code Name: %s State: %s", codeName, state);
        }
    }

