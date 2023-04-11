package components;

import jade.Component;

public class SpriteRenderer extends Component {

    private boolean firsTime = false;

    @Override
    public void start(){
        System.out.println("I am starting");
    }
    @Override
    public void update(float dt) {
        if (!firsTime){

        System.out.println("I am updating");
        firsTime = true;
        }
    }
}
