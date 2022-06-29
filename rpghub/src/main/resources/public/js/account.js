//Functions
function test_update_span(session){
    let element = document.getElementById("span-1")
    element.innerHTML = `user_id: ${session.user_id} is_mod: ${session.is_mod} is_frozen: ${session.is_frozen} error: ${session.error}`
}

function render_listings(all_listings){
    for (let listing_json of all_listings) {
        //Some function to create row 
    }
    //Populate page
}

//user_id
//list_name
//dungeonName
//max_size

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

async function get_all_listings(){
    try{
        const response = await fetch('/listing/all-listings')
        const results = await response.json()
        if(response.status =! 200){
            const message = `An error occured: Couldn't obtain listings  ${res.status}`
            throw message
        }
        return results
    }catch(e){
        console.log(e)
    }
}


get_session()
let all_listings = get_all_listings();
render_listings(all_list)