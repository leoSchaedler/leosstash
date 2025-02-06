public class Inter {


    ListaSE intersec;

    public Inter() {
    }


    public ListaSE interseçao(ListaSE x, ListaSE y) {
        intersec = new ListaSE();
        for(No i = x.getPrimeiro(); i!=null; i=i.getProximo()) {
            for(No j = y.getPrimeiro(); j!=null; j=j.getProximo()) {
                if(i.getDado() == j.getDado()) {
                    intersec.insereOrdenado(i.getDado());
                    break;}
            }

        }
        return intersec;

    }





}
