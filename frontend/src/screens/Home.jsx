import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './home.css'; // Correct relative path to import the CSS file
import Navbar from './Navbar'; // Adjust the path as necessary
import { FaInstagram, FaTwitter, FaLinkedin } from 'react-icons/fa';
function Home() {
  const [user, setUser] = useState({ name: 'John Doe', email: 'john@example.com' }); // Example user details
  const [category, setCategory] = useState('');
  const [faqIndex, setFaqIndex] = useState(null);
  const navigate = useNavigate();

  const categories = ['Wedding', 'Food', 'Nature', 'Travel']; // Example categories

  
  const faqs = [
    {
      question: 'What are ClickGenius photography services?',
      answer: 'Photography services offered on ClickGenius include portrait photography, events photography, real estate photography, food photography, and more. Beyond capturing photos, photographers on ClickGenius offer other photography-related services, such as photo retouching, background removal, model casting, and styling. Best of all, you can choose from a variety of local photography services near your area that fit your budget and timeframe.',
    },
    {
      question: 'Are ClickGenius photography services offered in-person or remotely?',
      answer: 'ClickGenius photographers offer three types of services: in-person photography, which involves face-to-face interaction between the client and photographer; remote photography, where the photographer is sent to the client\'s location to do a photoshoot; and product photography, where the client ships the product to the photographer for a photo session and the photographer returns the photos and product to the client.',
    },
    {
      question: 'What types of photographers can I hire on ClickGenius?',
      answer: 'There is a wide range of experts on ClickGenius who specialize in different types of photography. You’ll find portrait photographers, event photographers, product photographers, aerial photographers, lifestyle & fashion photographers, real estate photographers, and food photographers. Some photographers specialize in niches such as macro and bird photography, photography for acting portfolios, and interior design photography.',
    },
    {
      question: 'How do I choose the right photographer for my project?',
      answer: 'Choosing the right photographer requires taking the time to research your goals and expectations, as well as the type of photography you need. Make sure you know and understand the different types of photography and whether these will suit your needs or not. You’ll then need to review their ClickGenius profile portfolio to determine their unique skill set and style. Check reviews from previous clients to assess their reputation. Finally, you’ll want to check the photographer\'s location (if applicable to your needs.)',
    },
    {
      question: 'How can I find a photographer near my location?',
      answer: 'Whether based in the US or residing overseas, finding a photographer near your location is easy with ClickGenius. Simply type in the keyword of the service you’re looking for in the search bar, and then use the “Service Options” to filter photographers by city. You can also use keyword-specific searches - such as “photographer [location]” - to make things more convenient. Tip: If you live in a small town, you might want to use the nearest city’s location. You can then discuss travel arrangements with your photographer.',
    },
    {
      question: 'Why do I need professional photography for my business?',
      answer: 'Professional photography for businesses can range from employee portraits to campaigns and promo projects. These services can help establish your brand, enhance your brand relevancy and authority, communicate the quality of your products or services, and make your visuals look more authentic (rather than relying on stock photography.) Furthermore, high-quality images can make your content more engaging, helping you stand out from the competition.',
    },
  ]

  const handleCategoryChange = (e) => {
    setCategory(e.target.value);
    if (e.target.value) {
      navigate(`/category/${e.target.value.toLowerCase()}`);
    }
  };



  const toggleFaq = (index) => {
    setFaqIndex(faqIndex === index ? null : index);
  };
  

  return (
    <>
      <Navbar /> {/* Include the CustomNavbar component */}
      <div className='home-container'>
        <header className='home-header'>
          <h1 className='headline'>Welcome to the Home Page</h1>
           <div className='marquee-container'>
            <div className='marquee'>
              <img src='./img1.jpg' alt='Photography 1' />
              <img src='./img3.jpg' alt='Photography 2' />
              <img src='./img3.jpg' alt='Photography 3' />
              <img src='./img5.jpg' alt='Photography 4' />
              {/* Add more images as needed */}
            </div>
          </div>
          
        </header>
        <main>
          <div className='faq-container'>
            <h3>Frequently Asked Questions</h3>
            <ul className='faq-list'>
              {faqs.map((faq, index) => (
                <li key={index} className='faq-item'>
                  <div className='faq-question' onClick={() => toggleFaq(index)}>
                    {faq.question} <span className='arrow'>{faqIndex === index ? '▲' : '▼'}</span>
                  </div>
                  {faqIndex === index && <div className='faq-answer'>{faq.answer}</div>}
                </li>
              ))}
            </ul>
          </div>
        </main>
        <footer className='home-footer'>
          <div className='social-media'>
            <a href='https://www.instagram.com' target='_blank' rel='noopener noreferrer'>
              <FaInstagram /> {/* Instagram icon */}
            </a>
            <a href='https://www.twitter.com' target='_blank' rel='noopener noreferrer'>
              <FaTwitter /> {/* Twitter icon */}
            </a>
            <a href='https://www.linkedin.com' target='_blank' rel='noopener noreferrer'>
              <FaLinkedin /> {/* LinkedIn icon */}
            </a>
          </div>
        </footer>
      </div>
    </>
  );
}

export default Home;
