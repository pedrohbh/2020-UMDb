import React from 'react';
import ReactDOM from 'react-dom';

class DefaultForm extends React.Component
{
	constructor(props)
	{
		super(props);		
		this.state = {inputValue : ""};		
	}

	
	onSubmit() 
	{
		//TODO To be implemented
	}
	
	
	render() {  
      return (
        <div>
   
   <form>
   <input type="text" id="inputValue" value={{ this.state.inputValue }} />
		<External Component id="Icon" value={{ this.state.Icon }} />
		
	
	
	<button onClick="onSubmit">onSubmit</button>
	
	</form></div>
      );
    }
  }

}