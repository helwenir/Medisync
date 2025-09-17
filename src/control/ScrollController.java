package control;

import javafx.event.EventHandler;
import javafx.scene.input.ScrollEvent;
import vue.VueAddDPS;
import vue.VueEditDPS;
import vue.VueEditSecouriste;
import vue.VueRemoveSecouriste;
import vue.VueSecouristeAdmin;

public class ScrollController implements EventHandler<ScrollEvent>{
    
    private VueSecouristeAdmin vue;
    private VueRemoveSecouriste removeVue;
    private VueEditSecouriste editVue;
    private VueAddDPS addDPS;
    private VueEditDPS editDPS;
    private int i1=0;
    private int i2=0;
    private int i3=0;
    private int i4=0;

    public void setVueSecouristeAdmin(VueSecouristeAdmin v){
        this.vue=v;
    }
    
    public void setremoveVue(VueRemoveSecouriste v){
        this.removeVue=v;
    }

    public void setEditVue(VueEditSecouriste v){
        this.editVue=v;
    }

    public void setAddDPS(VueAddDPS vue){
        this.addDPS=vue;
    }

    public void setEditDPS(VueEditDPS v){
        this.editDPS=v;
    }

    @Override
    public void handle(ScrollEvent event){
        if (this.vue!=null){
            if(event.getSource()==this.vue.getCenterBox()){
                if(i1==15){
                    if(event.getDeltaY()<0){
                        this.vue.updateTextSecouristeDown();
                        this.i1=0;
                    }else if(event.getDeltaY()>0){
                        this.vue.updateTextSecouristeUp();
                        this.i1=0;
                    }
                }else{
                    this.i1++;
                }
            }
        }if(this.removeVue!=null){
            if(event.getSource()==this.removeVue.getCenterBox()){
                if(i2==15){
                    if(event.getDeltaY()<0){
                        this.removeVue.updateTextSecouristeDown();
                        this.i2=0;
                    }          
                    else if(event.getDeltaY()>0){
                        this.removeVue.updateTextSecouristeUp();
                        this.i2=0;
                }
            
            }
                else{
                    this.i2++;
                }
            }
        }
        if(this.editVue!=null){
            if(event.getSource()==this.editVue.getCenterBox()){
                if(this.i3==15){
                    if(event.getDeltaY()<0){
                        this.editVue.updateTextSecouristeDown();
                        this.i3=0;
                    }          
                    else if(event.getDeltaY()>0){
                        this.editVue.updateTextSecouristeUp();
                        this.i3=0;
                }
            }
                else{
                    this.i3++;
                }
            }
        }

        if(this.addDPS!=null){
            if(event.getSource()==this.addDPS.getCompBox()){
                if(this.i4==15){
                    if(event.getDeltaY()<0){
                        this.addDPS.updateTextDown();
                        this.i4=0;
                    }          
                    else if(event.getDeltaY()>0){
                        this.addDPS.updateTextUp();
                        this.i4=0;
                }
            }
                else{
                    this.i4++;
                }
            }
        }
        if(this.editDPS!=null){
            if(event.getSource()==this.editDPS.getCompBox()){
                if(this.i4==15){
                    if(event.getDeltaY()<0){
                        this.editDPS.updateTextDown();
                        this.i4=0;
                    }          
                    else if(event.getDeltaY()>0){
                        this.editDPS.updateTextUp();
                        this.i4=0;
                }
            }
                else{
                    this.i4++;
                }
            }
        }
    }
}
