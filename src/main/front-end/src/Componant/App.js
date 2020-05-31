import React, {Component} from 'react';
import GroupList from '../UI/groupList';
import { BrowserRouter , Switch , Route} from 'react-router-dom';
import AppNavbar from '../UI/AppNavbar';
import Home from './home';
import GroupEdit from './groupEdit';
import { CookiesProvider } from 'react-cookie';

class App extends Component{
  
  render(){

    return (
      <CookiesProvider>
      <BrowserRouter>
       <AppNavbar/>

       <Switch>
          <Route path='/' exact component={Home}/>
          <Route path='/groups' exact component={GroupList}/>
          <Route path='/groups/:id' component={GroupEdit}/>
        </Switch>
            
                 
      </BrowserRouter>
      </CookiesProvider>
    );
  }


}

export default App;
