//Functions
function test_update_span(session){
    let element = document.getElementById("span-1")
    console.log(session)
    element.innerHTML = `user_id: ${session.user_id} is_mod: ${session.is_mod} is_frozen: ${session.is_frozen}`
}

//Async functions
async function get_session() {
    try{
        const response = await fetch('/session')
        const pReq = await response.json()
        if (response.status != 200){
            const message = `Couldn't obtain requests! An error occured: ${res.status}`
            throw message
        }
        test_update_span(pReq)
    }catch(err){
        console.log(err)
    }
}

get_session()