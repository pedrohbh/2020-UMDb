import React from 'react';
import ReactDOM from 'react-dom';

class SingleReview extends React.Component
{
	constructor(props)
	{
		super(props);		
		this.state = {text : ""};		
	}

	
	
	render() {  
      return (
        <div><Rating ></Rating><p id="text">{{ text }}</p>
	<header />
	</div>
      );
    }
  }

}