using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using TuDien_WebAPI.Models;

namespace TuDien_WebAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class VocabulariesController : ControllerBase
    {
        private readonly TuDienAnhVietContext _context;

        public VocabulariesController(TuDienAnhVietContext context)
        {
            _context = context;
        }

        // GET: api/Vocabularies
        [HttpGet]
        public IEnumerable<Vocabulary> GetVocabulary()
        {
            return _context.Vocabulary;
        }

        // GET: api/Vocabularies/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetVocabulary([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var vocabulary = await _context.Vocabulary.FindAsync(id);

            if (vocabulary == null)
            {
                return NotFound();
            }

            return Ok(vocabulary);
        }

        // PUT: api/Vocabularies/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutVocabulary([FromRoute] int id, [FromBody] Vocabulary vocabulary)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != vocabulary.Matu)
            {
                return BadRequest();
            }

            _context.Entry(vocabulary).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!VocabularyExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/Vocabularies
        [HttpPost]
        public async Task<IActionResult> PostVocabulary([FromBody] Vocabulary vocabulary)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.Vocabulary.Add(vocabulary);
            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateException)
            {
                if (VocabularyExists(vocabulary.Matu))
                {
                    return new StatusCodeResult(StatusCodes.Status409Conflict);
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtAction("GetVocabulary", new { id = vocabulary.Matu }, vocabulary);
        }

        // DELETE: api/Vocabularies/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteVocabulary([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var vocabulary = await _context.Vocabulary.FindAsync(id);
            if (vocabulary == null)
            {
                return NotFound();
            }

            _context.Vocabulary.Remove(vocabulary);
            await _context.SaveChangesAsync();

            return Ok(vocabulary);
        }

        private bool VocabularyExists(int id)
        {
            return _context.Vocabulary.Any(e => e.Matu == id);
        }
    }
}