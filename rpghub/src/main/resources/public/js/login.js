//Functions
<<<<<<< HEAD
function test_update_span(session){
    let element = document.getElementById("span-1")
    console.log(session)
    element.innerHTML = `user_id: ${session.user_id} is_mod: ${session.is_mod} is_frozen: ${session.is_frozen} error: ${session.error}`
}
=======
// function test_update_span(session){
//     let element = document.getElementById("span-1")
//     console.log(session)
//     element.innerHTML = `user_id: ${session.user_id} is_mod: ${session.is_mod} is_frozen: ${session.is_frozen} error: ${session.error}`
// }
>>>>>>> listingpage_backup

//Async functions
async function get_session() {
    try{
        const response = await fetch('/session')
        const pReq = await response.json()
        if (response.status != 200){
            const message = `Couldn't obtain requests! An error occured: ${res.status}`
            throw message
        }
<<<<<<< HEAD
        test_update_span(pReq)
=======
>>>>>>> listingpage_backup
    }catch(err){
        console.log(err)
    }
}

<<<<<<< HEAD
get_session()
=======
//get_session()
>>>>>>> listingpage_backup
