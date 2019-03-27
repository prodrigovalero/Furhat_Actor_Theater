package furhatos.app.actor_theater.flow

import furhatos.nlu.common.*
import furhatos.flow.kotlin.*

import furhatos.app.actor_theater.nlu.*;
import furhatos.gestures.Gestures
import furhatos.gestures.Gestures.ExpressAnger
import furhatos.gestures.Gestures.Smile
import furhatos.util.Gender
import furhatos.util.Language

var count = 0

val Start : State = state(Interaction) {


        onEntry {
        System.out.println("Hey there")
        furhat.ask("Are you ready to start?")
        }


        onResponse<Yes> {
            furhat.say("Let's get to it")
            goto(Personaje)



        }

        onResponse<No> {
            furhat.say("I'll wait, but don't take to long")
            furhat.gesture(Smile)
            delay(4000)
            goto(loop)
        }



}

val Personaje :State = state(Interaction)
{
    onEntry{
        delay(500)
        furhat.ask("Should I do Juliet and you do Romeo?")
    }

    onResponse<Yes> {
        furhat.setVoice(Language.ENGLISH_US, Gender.FEMALE)
        goto(Section1)

    }
    onResponse<No> {
        furhat.setVoice(Language.ENGLISH_US, Gender.MALE)
        goto(Section1)
    }


}

val Section1 : State = state(Interaction) {
    onEntry{
        furhat.ask(HamletSection1)

    }
    System.out.println("Say it now! ( First section) ")

    onResponse<Section1Intent>{
        furhat.say("Fist test passed!")
        goto(Section2)
    }

    System.out.println("in between section 1")
    onResponse{
        addOnetoCount()
        goto(RestartFunction)
    }

}

val Section2 : State = state(Interaction) {
    onEntry{
        furhat.ask(HamletSection2)

    }
    System.out.println("Say it now! ( Second section) ")

    onResponse<Section2Intent>{
        furhat.say("Second test passed!", async = false)
    }

    onResponse{
        addOnetoCount()
        goto(RestartFunction)
    }

}

val RestartFunction : State = state(Interaction){
    onEntry {


        delay(2000)

        if(numErrors() >= 3){
            furhat.say("You have already done 3 errors, this is unbelivable, study and come back tommorow!", async = true)
            furhat.gesture(ExpressAnger(duration=2.0))
        }
        else {
            furhat.say("You did a small error, don't worry let's restart")
            delay(1000)
            goto(loop)
        }
    }
}
val loop : State = state(Interaction){
     onEntry {


         furhat.say("I hope you are ready now")
         delay(1000)
         goto(Start)
     }
}

fun addOnetoCount() {
    count = count + 1
}
fun numErrors () : Int{
    System.out.println("Retourning count: " + count)
    return count
}


val greeting = utterance {
    +"Hi there,"
    +Gestures.Smile
    +"Welcome to the Furhat Theater Academy, today we are going to rehearse a well-known play, Hamlet"
    +delay(500)
    +"I am sure you have rehearse and you know your text well, because I know mine"
    +Gestures.BigSmile()
    +delay(2000)

}

val HamletSection1 = utterance {
    +delay(500)
    +"There was a time,"
}

val HamletSection2 = utterance {
    +delay(500)
    +"There was a second chance"
}

