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
   <input type="text" id="inputValue"value={{ this.state.inputValue }}/><Icon id="Icon"/>
	
	
	<button onClick="onSubmit">onSubmit</button>
	
	</form></div>
      );
    }
  }

}